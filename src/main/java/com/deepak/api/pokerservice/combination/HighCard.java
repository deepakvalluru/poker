package com.deepak.api.pokerservice.combination;

import com.deepak.api.pokerservice.model.Result;
import com.deepak.api.pokerservice.model.SetOfCards;
import com.deepak.api.pokerservice.model.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class HighCard
{

   public static Result isValid( SetOfCards setOfCards )
   {
      SetOfCards cards = new SetOfCards();
      cards.addCards( getHighCard( setOfCards ) );
      return new Result( true, cards );
   }

   public static List<Card> getHighCard( SetOfCards setOfCards )
   {
      Collections.sort( setOfCards.getCards() );
      return setOfCards.getCards().stream().sorted().limit( 5 ).collect( Collectors.toList() );
   }

}
