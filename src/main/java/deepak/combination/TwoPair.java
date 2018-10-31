package deepak.combination;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

public class TwoPair
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map< Integer, List< Card > > cardMap =
                                           setOfCards.getCards()
                                                     .stream()
                                                     .collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      List< Integer > list = cardMap.keySet()
                                    .stream()
                                    .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 2 ) )
                                    .collect( Collectors.toList() );

      if( list != null && list.size() >= 2 )
      {
         Collections.sort( list, Collections.reverseOrder() );
         SetOfCards cards = new SetOfCards();
         List< Integer > topTwo = list.stream().sorted( Collections.reverseOrder() ).limit( 2 ).collect( Collectors.toList() ); 
         topTwo.stream().forEachOrdered( x -> cards.addCards( cardMap.get( x ) ) );
         
         Optional< Integer > fifthCardNumber = cardMap.keySet().stream().filter( x -> ! topTwo.contains( x ) ).max( Collections.reverseOrder() );
         
         if( fifthCardNumber.isPresent() )
         {
            cards.addCard( cardMap.get( fifthCardNumber.get() ).get( 0 ) );
         }
         
         return new Result( true, cards );
      }
      
      return new Result( false, null) ;
   }
}
