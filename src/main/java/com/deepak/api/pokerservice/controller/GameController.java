package com.deepak.api.pokerservice.controller;

import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.CardDeal;
import com.deepak.api.pokerservice.model.Game;
import com.deepak.api.pokerservice.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/pokergame")
public class GameController
{
   private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

   @RequestMapping( method= RequestMethod.GET, path="/new")
   @ResponseBody
   public Game newGame()
   {
      List<Player> players = IntStream.rangeClosed( 1, 10 )
                                      .mapToObj( pos -> new Player( "Player" + pos, pos ) )
                                      .collect( Collectors.toList() );
      return new Game( players );
   }

   @RequestMapping( method= RequestMethod.POST, path="/deal")
   @ResponseBody
   public Game dealRandomCard( @RequestBody CardDeal cardDeal )
   {
      if( !isValid( cardDeal) )
      {
         return cardDeal.getGame();
      }
      Game currentGame = cardDeal.getGame();
      Card dealtCard = cardDeal.getDealtCard();
      List<Card> deckCards = currentGame.getDeck().getCards();
      if( dealtCard == null )
      {
         dealtCard = deckCards.stream()
                              .skip( new Random().nextInt( deckCards.size() ) )
                              .findFirst()
                              .orElse( deckCards.get( 0 ) );
         cardDeal.setDealtCard( dealtCard );
      }

      if( cardDeal.isBoardCard() )
      {
         currentGame.dealBoardCard( dealtCard );
      }
      else if( cardDeal.isPlayerCard() && cardDeal.getPlayer() != null )
      {
         currentGame.dealPlayerCard( dealtCard, cardDeal.getPlayer() );
      }

      return currentGame;
   }

   private boolean isValid( CardDeal cardDeal )
   {
      Game currentGame = cardDeal.getGame();
      List<Card> deckCards = currentGame.getDeck().getCards();
      Card dealtCard = cardDeal.getDealtCard();
      if( dealtCard != null && !deckCards.stream().anyMatch( card -> dealtCard.equals( card ) ) )
      {
         logger.error( "Unable to find the given card in the deck. It may have already been dealt" );
         return false;
      }
      if( cardDeal.isPlayerCard() && cardDeal.getPlayer() != null && currentGame.getPlayers()
                                                                                .stream()
                                                                                .anyMatch( p -> p.equals( cardDeal.getPlayer() )
                                                                                                && p.getPlayerCards()
                                                                                                    .getCards()
                                                                                                    .size() >= 2 ) )
      {
         logger.error( "{} has already been dealt 2 cards. It's the maximum.", cardDeal.getPlayer().getName() );
         return false;
      }
      return true;
   }
}
