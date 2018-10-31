package deepak.combination;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deepak.Game;
import deepak.PokerEngine;
import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.BestHand;
import deepak.pojo.Card;
import deepak.pojo.Player;
import deepak.type.Number;
import deepak.type.Suit;
import deepak.validator.PokerHandValidator;

public class StraightTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testStraight()
   {
      Card card1 = new Card( Number.EIGHT, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.NINE, Suit.HEARTS );
      Card card4 = new Card( Number.TEN, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card7 = new Card( Number.FIVE, Suit.CLUBS );
      
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }
   
   @Test
   public void testStraightWithA2345()
   {
      Card card1 = new Card( Number.EIGHT, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.KING, Suit.HEARTS );
      Card card4 = new Card( Number.FOUR, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card7 = new Card( Number.FIVE, Suit.CLUBS );
      Card card8 = new Card( Number.THREE, Suit.SPADES );
      Card card9 = new Card( Number.TWO, Suit.SPADES );
      
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      setOfCards.addCard( card9 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }

   @Test
   public void testTwoSraightHands()
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
                                       .add( new Card( Number.FIVE, Suit.CLUBS ) )
                                       .add( new Card( Number.TWO, Suit.CLUBS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player2.setPlayerCards( new SetOfCards( playerCards2 ) );
      
      Game straightCardsPoker = new Game( Stream.< Player > builder()
                                           .add( player1 )
                                           .add( player2 )
                                           .build()
                                           .collect( Collectors.toList() ) );
      
      straightCardsPoker.dealCard( new Card( Number.ACE, Suit.HEARTS ) );
      straightCardsPoker.dealCard( new Card( Number.QUEEN, Suit.SPADES ) );
      straightCardsPoker.dealCard( new Card( Number.THREE, Suit.HEARTS ) );
      straightCardsPoker.dealCard( new Card( Number.FOUR, Suit.SPADES ) );
      straightCardsPoker.dealCard( new Card( Number.JACK, Suit.SPADES ) );
      
      logger.info( "Board Cards - {} \n", straightCardsPoker.getBoardCards() );
      GameResult gameResult = PokerEngine.getResult( straightCardsPoker );
      logger.info( "Winners are - {} \n", gameResult.getWinners() );
   }
}  
