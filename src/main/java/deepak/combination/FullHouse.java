package deepak.combination;

import java.util.*;
import java.util.stream.Collectors;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;

public class FullHouse
{
   public static Result isValid( SetOfCards setOfCards )
   {
      //TODO - Fix bug where there could be two sets of three cards ( e.g. 3,3,3,4,4,4,7)
      Map< Integer, List< Card > > cardMap =
                                           setOfCards.getCards()
                                                     .stream()
                                                     .collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      Optional<Integer> threeCard = cardMap.keySet()
                                           .stream()
                                           .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 3 ) )
                                           .sorted( Collections.reverseOrder() )
                                           .findFirst();

      Optional<Integer> twoCard = null;

      if( threeCard.isPresent() )
      {
         twoCard = cardMap.keySet()
                                            .stream()
                                            .filter( x -> ( cardMap.get( x ) != null && !cardMap.get( x ).equals( threeCard ) && cardMap.get( x ).size() >= 2 ) )
                                            .sorted( )
                                            .findFirst();
      }


      if( threeCard != null && twoCard != null && threeCard.isPresent() && twoCard.isPresent() )
      {
         SetOfCards cards = new SetOfCards();
         cards.addCards( cardMap.get( threeCard.get( ) ) );
         cards.addCards( cardMap.get( twoCard.get( ) ).stream().limit( 2 ).collect( Collectors.toList()) );
         return new Result( true, cards );
      }
      
      return new Result( false, null) ;
   }
}
