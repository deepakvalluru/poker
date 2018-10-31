package deepak.combination;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

public class ThreeOfaKind
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map< Integer, List< Card > > cardMap =
                                           setOfCards.getCards()
                                                     .stream()
                                                     .collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      Optional< Integer > topSetNumber = cardMap.keySet()
                                                .stream()
                                                .sorted( Collections.reverseOrder() )
                                                .filter( x -> ( cardMap.get( x ) != null
                                                                && cardMap.get( x ).size() == 3 ) )
                                                .findFirst();

      if( topSetNumber.isPresent() )
      {
         SetOfCards cards = new SetOfCards();
         cards.addCards( cardMap.get( topSetNumber.get() ) );

         List< Integer > nextTwoHighCardNumbers = cardMap.keySet()
                                                         .stream()
                                                         .filter( x -> x != topSetNumber.get() )
                                                         .sorted( Collections.reverseOrder() )
                                                         .limit( 2 )
                                                         .collect( Collectors.toList() );
         
         nextTwoHighCardNumbers.stream().forEachOrdered( x -> cards.addCards( cardMap.get( x ) ) );

         return new Result( true, cards );
      }

      return new Result( false, null );
   }
}
