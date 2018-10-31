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

public class FourOfaKindTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void testFourOfaKind()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.ACE, Suit.SPADES );
      Card card5 = new Card( Number.ACE, Suit.CLUBS );
      Card card6 = new Card( Number.KING, Suit.SPADES );
      Card card7 = new Card( Number.TWO, Suit.CLUBS );
      Card card8 = new Card( Number.TWO, Suit.HEARTS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      
      logger.info( "Board Cards : {}", setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      logger.info( "Best Hand: {}", bestHand );
   }
}
