package com.deepak.api.pokerservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Deck
{
   private List<Card> cards = getAllCards();
   private static Map<Number, String> numberMap = new HashMap<>(  );
   private static Map<Suit, String> suitMap = new HashMap<>(  );

   static {
      numberMap.put( Number.TWO, "2" );
      numberMap.put( Number.THREE, "3" );
      numberMap.put( Number.FOUR, "4" );
      numberMap.put( Number.FIVE, "5" );
      numberMap.put( Number.SIX, "6" );
      numberMap.put( Number.SEVEN, "7" );
      numberMap.put( Number.EIGHT, "8" );
      numberMap.put( Number.NINE, "9" );
      numberMap.put( Number.TEN, "10" );
      numberMap.put( Number.JACK, "J" );
      numberMap.put( Number.QUEEN, "Q" );
      numberMap.put( Number.KING, "K" );
      numberMap.put( Number.ACE, "A" );

      suitMap.put( Suit.CLUBS, "C" );
      suitMap.put( Suit.SPADES, "S" );
      suitMap.put( Suit.HEARTS, "H" );
      suitMap.put( Suit.DIAMONDS, "D" );
   }

   public Deck()
   {

   }

   public List<Card> getCards()
   {
      return cards;
   }

   private List<Card> getAllCards()
   {
      cards = new ArrayList<>();
      for( Suit suit : Suit.values() )
      {
         for( Number number : Number.values() )
         {
            Card card = new Card( number, suit );
            card.setImagePath( getImageLink( number, suit ) );
            cards.add( card );
         }
      }

       return cards;
   }

   private String getImageLink( Number number, Suit suit )
   {
      return "/images/" + numberMap.get( number ) + suitMap.get( suit ) + ".png";
   }

}
