package deepak.combination;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.type.Suit;

public class StraightFlush
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map< Suit, List< Card > > cardMap = setOfCards.getCards()
                                                    .stream()
                                                    .collect( Collectors.groupingBy( x -> x.getSuit() ) );

      Optional< Suit > suit = cardMap.keySet()
                                     .stream()
                                     .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() >= 5 ) )
                                     .findFirst();

      if( suit.isPresent() )
      {
         List< Card > list = cardMap.get( suit.get() );
         Collections.sort( list );
         SetOfCards cards = new SetOfCards( list );
         Result straightResult = Straight.isValid( cards );
         return straightResult;
      }
      
      return new Result( false, null );
   }
}
