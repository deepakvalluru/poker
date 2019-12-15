package com.deepak.api.pokerservice.combination;

import com.deepak.api.pokerservice.model.Result;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.Card;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FourOfaKind
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map<Integer, List<Card>> cardMap =
               setOfCards.getCards().stream().collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      List<Integer> fourCard = cardMap.keySet()
                                      .stream()
                                      .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() == 4 ) )
                                      .collect( Collectors.toList() );

      if( fourCard != null && !fourCard.isEmpty() )
      {
         SetOfCards cards = new SetOfCards();
         cards.addCards( cardMap.get( fourCard.get( 0 ) ) );
         Optional<Integer> fifthCard = cardMap.keySet()
                                              .stream()
                                              .filter( x -> x != fourCard.get( 0 ) )
                                              .sorted( Collections.reverseOrder() )
                                              .limit( 1 )
                                              .findFirst();
         if( fifthCard.isPresent() )
         {
            cards.addCard( cardMap.get( fifthCard.get() ).get( 0 ) );
         }
         return new Result( true, cards );
      }

      return new Result( false, null );
   }
}
