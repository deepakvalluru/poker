package com.deepak.api.pokerservice.service;

import com.deepak.api.pokerservice.model.GameResult;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.Deck;
import com.deepak.api.pokerservice.model.Game;
import com.deepak.api.pokerservice.model.Player;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PercentageCalculator
{
   private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

   private static final ExecutorService executorService = Executors.newFixedThreadPool( 20 );

   public static GameResult calculatePercentages( Game game )
   {
      List<List<Card>> remainingCardCombinations =
               getCombinations( game.getDeck(), 5 - game.getBoardCards().getCards().size() );
      Map<Player, Fraction> playerWinMap = new HashMap<>();
      List<CompletableFuture<GameResult>> futureResults = new ArrayList<>();

      for( List<Card> list : remainingCardCombinations )
      {
         Game cloneGame = Game.cloneGame( game );
         SetOfCards boardCards = cloneGame.getBoardCards();
         boardCards.addCards( list );
         logger.trace( " CARDS ON THE BOARD - {}", boardCards );
         cloneGame.getPlayers().forEach( p -> p.setBoardCards( boardCards ) );
         CompletableFuture<GameResult> future =
                  CompletableFuture.supplyAsync( () -> PokerEngine.getResult( cloneGame ), executorService );
         futureResults.add( future );
      }

      // blocking call - will wait until all futures are implemented
      CompletableFuture.allOf( all( futureResults ) );

      futureResults.forEach( future -> {
         try
         {
            GameResult gameResult = future.get();
            if( gameResult.getIsResultAvailable() )
            {
               // if multiple winners, then only add winning fraction share.
               Fraction fr = new Fraction( 1, gameResult.getWinners().size() );
               gameResult.getWinners().forEach( x -> {
                  playerWinMap.merge( x, fr, ( y, z ) -> y.add( z ) );
                  logger.trace( x.getName() + " with winning hand " + x.getBestHand() );
               } );
            }
         }
         catch( InterruptedException e )
         {
            e.printStackTrace();
         }
         catch( ExecutionException e )
         {
            e.printStackTrace();
         }

      } );

      playerWinMap.keySet().forEach( x -> logger.info( "{} - {}", x.getName(), playerWinMap.get( x ).floatValue() ) );

      GameResult gameResult = new GameResult();
      List<Player> activePlayers = game.getPlayers().stream().filter( Player::isActive ).collect( Collectors.toList());
      gameResult.setPlayers( activePlayers );

      gameResult.setGame( game );
      gameResult.getPlayers().forEach( p -> {
         p.setPercentage( playerWinMap.get( p ) != null ? ( playerWinMap.get( p )
                                                                        .divide( remainingCardCombinations.size() )
                                                                        .multiply( 100 )
                                                                        .floatValue() ) : 0f );
         p.setBoardCards( game.getBoardCards() );
      } );

      logger.info( "-------------------------------------Here are the results--------------------------------" );
      gameResult.getPlayers().forEach( p -> logger.info( "{} -----------> {} %", p.getName(),  String.format( "%.2f", p.getPercentage() ) ) );

      return gameResult;
   }

   private static List<List<Card>> getCombinations( Deck deck, int cardsToDeal )
   {
      List<Card> cards = deck.getCards();
      int cardsLeftInDeck = cards.size();
      Iterator<int[]> c = CombinatoricsUtils.combinationsIterator( cardsLeftInDeck, cardsToDeal );
      List<List<Card>> list = new ArrayList<>();
      c.forEachRemaining( x -> list.add( IntStream.of( x )
                                                  .boxed()
                                                  .map( y -> cards.get( y ) )
                                                  .collect( Collectors.toList() ) ) );
      logger.info( "Total remaining combinations - {}", list.size() );
      return list;
   }

   private static <T> CompletableFuture<List<T>> all( List<CompletableFuture<T>> futures )
   {
      CompletableFuture[] cfs = futures.toArray( new CompletableFuture[ futures.size() ] );

      return CompletableFuture.allOf( cfs )
                              .thenApply( ignored -> futures.stream()
                                                            .map( CompletableFuture::join )
                                                            .collect( Collectors.toList() ) );
   }
}
