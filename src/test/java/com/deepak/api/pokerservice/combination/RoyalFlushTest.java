package com.deepak.api.pokerservice.combination;

import com.deepak.api.pokerservice.model.Number;
import com.deepak.api.pokerservice.model.*;
import com.deepak.api.pokerservice.service.PokerHandValidator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class RoyalFlushTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testRoyalFlush()
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
      Card card11 = new Card( Number.ACE, Suit.CLUBS );
      
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
      setOfCards.addCard( card11 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }
}
