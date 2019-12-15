package com.deepak.api.pokerservice.model;

import com.deepak.api.pokerservice.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SetOfCards
{
   private List<Card> cards;

   public SetOfCards()
   {

   }

   public SetOfCards( List<Card> cards )
   {
      this.cards = cards;
   }

   public List<Card> getCards()
   {
      if( this.cards == null )
      {
         cards = new ArrayList<>();
      }
      return cards;
   }

   public void setCards( List<Card> cards )
   {
      this.getCards().clear();
      this.cards = cards;
   }

   public void addCard( Card card )
   {
      this.getCards().add( card );
   }

   public void addCards( List<Card> cards )
   {
      this.getCards().addAll( cards );
   }

   public String toString()
   {
      return this.getCards().stream().map( x -> x.toString() ).collect( Collectors.joining( ", " ) );
   }
}
