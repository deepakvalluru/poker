package deepak;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import deepak.common.Result;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.pojo.Deck;
import deepak.pojo.Player;

public class PercentageCalculator
{
   private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

   private static ExecutorService executorService = Executors.newFixedThreadPool( 20);

   public static GameResult calculatePercentages( Game game )
   {
      List< List< Card > > remainingCardCombinations = getCombinations( game.getDeck(),
                                                                        5 - game.getBoardCards().getCards().size() );
      Map< Player, Integer > playerWinMap = new HashMap<>();
      List< Card > currentBoardCards = game.getBoardCards().getCards();
      List<Future<GameResult>> futureResults = new ArrayList<>(  );


      for( List< Card > list : remainingCardCombinations )
      {
         Game cloneGame = Game.cloneGame( game );
         SetOfCards boardCards = cloneGame.getBoardCards();
         boardCards.addCards( list );
         logger.info( " CARDS ON THE BOARD - {}", boardCards );
         for( Player player : game.getPlayers() )
         {
            player.setBoardCards( boardCards );
         }
         Future< GameResult > futureResult = executorService.submit( () -> PokerEngine.getResult( cloneGame ) );
         futureResults.add( futureResult );
      }

      for( Future<GameResult> futureResult : futureResults )
      {
         GameResult gameResult = null;
         try
         {
            gameResult = futureResult.get();
            if( gameResult.getIsResultAvailable() )
            {
               gameResult.getWinners().forEach( x -> { playerWinMap.merge( x, 1, ( y, z ) -> y + z );
               logger.info( x.getName() + " with winning hand " + x.getBestHand() );} );
            }
         }
         catch (InterruptedException | ExecutionException e)
         {
            e.printStackTrace();
            logger.error( "Executor service exception : {}", e.getMessage() );
         }
//         finally {
//            if (executorService != null) {
//               executorService.shutdownNow();
//            }
//         }
      }


      playerWinMap.keySet().forEach( x -> logger.info( "{} - {}", x.getName(), playerWinMap.get( x ) ) );
      GameResult gameResult = new GameResult();
      gameResult.setPlayers( game.getPlayers() );
      gameResult.getPlayers().forEach( p -> {
         p.setPercentage( playerWinMap.get( p ) != null ? ( ( float ) playerWinMap.get( p )
                                                            / remainingCardCombinations.size() )
                                                          * 100
                                                        : 0f );
         p.setBoardCards( game.getBoardCards() );
      } );

      return gameResult;
   }

   public static List< List< Card > > getCombinations( Deck deck, int cardsToDeal )
   {
      List< Card > cards = deck.getCards();
      int cardsLeftInDeck = cards.size();
      Iterator< int[] > c = CombinatoricsUtils.combinationsIterator( cardsLeftInDeck, cardsToDeal );
      List< List< Card > > list = new ArrayList<>();
      c.forEachRemaining( x -> list.add( IntStream.of( x )
                                                  .boxed()
                                                  .map( y -> cards.get( y ) )
                                                  .collect( Collectors.toList() ) ) );
      logger.info( "Total remaining combinations - {}", list.size() );
      return list;
   }
}
