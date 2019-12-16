package com.deepak.api.pokerservice.combination;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepak.api.pokerservice.model.Game;
import com.deepak.api.pokerservice.service.PokerEngine;
import com.deepak.api.pokerservice.model.GameResult;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.BestHand;
import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.Player;
import com.deepak.api.pokerservice.model.Number;
import com.deepak.api.pokerservice.model.Suit;
import com.deepak.api.pokerservice.service.PokerHandValidator;

public class HighCardTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testHighCard()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.FOUR, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.KING, Suit.SPADES );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }
   
   @Test
   public void testTwoHighCardHands()
   {
      Player player1 = new Player( "player one" );
      List< Card > playerCards1 = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.SPADES ) )
                                       .add( new Card( Number.TEN, Suit.DIAMONDS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player1.setPlayerCards( new SetOfCards( playerCards1 ) );
      
      Player player2 = new Player( "player Two" );
      List< Card > playerCards2 = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.CLUBS ) )
                                       .add( new Card( Number.TEN, Suit.CLUBS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player2.setPlayerCards( new SetOfCards( playerCards2 ) );
      
      Game highCardPoker = new Game( Stream.< Player > builder()
                                           .add( player1 )
                                           .add( player2 )
                                           .build()
                                           .collect( Collectors.toList() ) );
      
      highCardPoker.dealBoardCard( new Card( Number.ACE, Suit.HEARTS ) );
      highCardPoker.dealBoardCard( new Card( Number.EIGHT, Suit.SPADES ) );
      highCardPoker.dealBoardCard( new Card( Number.SIX, Suit.HEARTS ) );
      highCardPoker.dealBoardCard( new Card( Number.FOUR, Suit.SPADES ) );
      highCardPoker.dealBoardCard( new Card( Number.JACK, Suit.SPADES ) );
      
      logger.info( "Board Cards - {} \n ", highCardPoker.getBoardCards() );
      GameResult gameResult = PokerEngine.getResult( highCardPoker );
      logger.info( "Winners are - \n {}", gameResult.getWinners() );
   }
}
