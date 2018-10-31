package deepak.combination;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

public class FullHouse
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map< Integer, List< Card > > cardMap =
                                           setOfCards.getCards()
                                                     .stream()
                                                     .collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      List< Integer > threeCard = cardMap.keySet()
                                         .stream()
                                         .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 3 ) )
                                         .collect( Collectors.toList() );

      List< Integer > twoCard = cardMap.keySet()
                                       .stream()
                                       .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 2 ) )
                                       .collect( Collectors.toList() );

      if( threeCard != null && !threeCard.isEmpty() && twoCard != null && !twoCard.isEmpty() )
      {
         SetOfCards cards = new SetOfCards();
         threeCard.stream().sorted( Collections.reverseOrder() ).limit( 1 ).forEach( x -> cards.addCards( cardMap.get( x ) ) );
         twoCard.stream().sorted( Collections.reverseOrder() ).limit( 1 ).forEach( x -> cards.addCards( cardMap.get( x ) ) );
         return new Result( true, cards );
      }
      
      return new Result( false, null) ;
   }
}
