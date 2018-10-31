package deepak.validator;

import deepak.combination.Flush;
import deepak.combination.FourOfaKind;
import deepak.combination.FullHouse;
import deepak.combination.HighCard;
import deepak.combination.Pair;
import deepak.combination.RoyalFlush;
import deepak.combination.Straight;
import deepak.combination.StraightFlush;
import deepak.combination.ThreeOfaKind;
import deepak.combination.TwoPair;
import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.BestHand;
import deepak.type.Hand;

public class PokerHandValidator 
{
   
	public static BestHand getBestHand( SetOfCards setOfCards )
	{
		Result result;
		
		// ROYAL FLUSH CHECK
      result = RoyalFlush.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.ROYAL_FLUSH, result.getBestSetOfCards() );
      }
		
		// STRAIGHT FLUSH CHECK
      result = StraightFlush.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.STRAIGHT_FLUSH, result.getBestSetOfCards() );
      }
      
		// FOUR OF A KIND CHECK
      result = FourOfaKind.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.FOUR_OF_A_KIND, result.getBestSetOfCards() );
      }
		
		// FULL HOUSE CHECK
      result = FullHouse.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.FULL_HOUSE, result.getBestSetOfCards() );
      }
      
		// FLUSH CHECK
      result = Flush.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.FLUSH, result.getBestSetOfCards() );
      }
      
		// STRAIGHT CHECK
      result = Straight.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.STRAIGHT, result.getBestSetOfCards() );
      }
		
		// THREE OF A KIND CHECK
      result = ThreeOfaKind.isValid( setOfCards );
      if( result.isValid() )
      {
         return new BestHand( Hand.THREE_OF_A_KIND, result.getBestSetOfCards() );
      }
		
		// TWO PAIR CHECK
		result = TwoPair.isValid( setOfCards );
		if( result.isValid() )
      {
         return new BestHand( Hand.TWO_PAIR, result.getBestSetOfCards() );
      }
		
		// PAIR CHECK
		result = Pair.isValid( setOfCards );
		if( result.isValid() )
		{
		   return new BestHand( Hand.PAIR, result.getBestSetOfCards() );
		}
		
		// HIGH CARD CHECK
		result = HighCard.isValid( setOfCards );
		if( result.isValid() )
		{
			return new BestHand( Hand.HIGH_CARD, result.getBestSetOfCards() ); 
		}
		
		return new BestHand( Hand.NOTHING, null );
	}
	
	
}
