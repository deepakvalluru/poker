package deepak.combination;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

public final class HighCard
{

   public static Result isValid( SetOfCards setOfCards )
   {
      SetOfCards cards = new SetOfCards();
      cards.addCards( getHighCard( setOfCards ) );
      return new Result( true, cards );
   }

   public static List< Card > getHighCard( SetOfCards setOfCards )
   {
      Collections.sort( setOfCards.getCards() );
      return setOfCards.getCards().stream().limit( 5 ).collect( Collectors.toList() );
   }
   
}
