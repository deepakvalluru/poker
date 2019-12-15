package com.deepak.api.pokerservice.combination;

import com.deepak.api.pokerservice.model.Result;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.Card;
import com.deepak.api.pokerservice.model.Suit;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Flush
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map<Suit, List<Card>> cardMap =
               setOfCards.getCards().stream().collect( Collectors.groupingBy( x -> x.getSuit() ) );

      Optional<Suit> suit = cardMap.keySet()
                                   .stream()
                                   .filter( x -> ( cardMap.get( x ) != null && cardMap.get( x ).size() >= 5 ) )
                                   .findFirst();

      if( suit.isPresent() )
      {
         List<Card> list = cardMap.get( suit.get() );
         Collections.sort( list );
         SetOfCards cards = new SetOfCards( list.stream().limit( 5 ).collect( Collectors.toList() ) );
         return new Result( true, cards );
      }

      return new Result( false, null );
   }
}
