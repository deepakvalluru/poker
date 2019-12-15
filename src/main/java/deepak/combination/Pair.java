package deepak.combination;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pair
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map<Integer, List<Card>> cardMap =
               setOfCards.getCards().stream().collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      Optional<Integer> number = cardMap.keySet()
                                        .stream()
                                        .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 2 ) )
                                        .findFirst();

      if( number.isPresent() )
      {
         SetOfCards cards = new SetOfCards( cardMap.get( number.get() ) );
         List<Integer> nextThreeNumbers = cardMap.keySet()
                                                 .stream()
                                                 .filter( x -> x != number.get() )
                                                 .sorted( Collections.reverseOrder() )
                                                 .limit( 3 )
                                                 .collect( Collectors.toList() );
         nextThreeNumbers.stream().forEachOrdered( x -> cards.addCards( cardMap.get( x ) ) );
         return new Result( true, cards );
      }

      return new Result( false, null );
   }
}
