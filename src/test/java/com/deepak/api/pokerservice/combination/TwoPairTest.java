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

public class TwoPairTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testTwoPair()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      
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
}  
