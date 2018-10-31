package deepak.combination;

import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deepak.common.SetOfCards;
import deepak.pojo.BestHand;
import deepak.pojo.Card;
import deepak.type.Number;
import deepak.type.Suit;
import deepak.validator.PokerHandValidator;

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
