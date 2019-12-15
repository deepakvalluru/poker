package com.deepak.api.pokerservice.combination;

import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.BestHand;
import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.Number;
import com.deepak.api.pokerservice.model.Suit;
import com.deepak.api.pokerservice.service.PokerHandValidator;

public class StraightFlushTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testStraightFlush()
   {
      Card card1 = new Card( Number.TWO, Suit.CLUBS );
      Card card2 = new Card( Number.THREE, Suit.CLUBS );
      Card card3 = new Card( Number.FOUR, Suit.CLUBS );
      Card card4 = new Card( Number.FIVE, Suit.CLUBS );
      Card card5 = new Card( Number.SIX, Suit.CLUBS );
      Card card6 = new Card( Number.NINE, Suit.CLUBS );
      Card card7 = new Card( Number.TEN, Suit.CLUBS );
      Card card8 = new Card( Number.JACK, Suit.CLUBS );
      Card card9 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card10 = new Card( Number.KING, Suit.CLUBS );
      
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
      setOfCards.addCard( card10 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }
}
