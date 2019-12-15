package com.deepak.api.pokerservice.combination;

import com.deepak.api.pokerservice.model.Result;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.Number;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoyalFlush
{
   private static List<Number> royalFlushNumbers = Collections.unmodifiableList( Arrays.asList( Number.ACE,
                                                                                                Number.KING,
                                                                                                Number.QUEEN,
                                                                                                Number.JACK,
                                                                                                Number.TEN ) );

   public static Result isValid( SetOfCards setOfCards )
   {
      Map<Integer, List<Card>> cardMap =
               setOfCards.getCards().stream().collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      if( cardMap.keySet()
                 .containsAll( royalFlushNumbers.stream().map( Number::getValue ).collect( Collectors.toList() ) ) )
      {
         return StraightFlush.isValid( setOfCards );
      }
      return new Result( false, null );
   }
}
