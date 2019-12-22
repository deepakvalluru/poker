package com.deepak.api.pokerservice.model;

public class Card implements Comparable<Card>
{

   private Number number;
   private Suit   suit;
   private String imagePath;

   public Card()
   {

   }

   public Card( Number number, Suit suit )
   {
      this.number = number;
      this.suit = suit;
   }

   public Number getNumber()
   {
      return number;
   }

   public void setNumber( Number number )
   {
      this.number = number;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public void setSuit( Suit suit )
   {
      this.suit = suit;
   }

   public String getImagePath()
   {
      return imagePath;
   }

   public void setImagePath( String imagePath )
   {
      this.imagePath = imagePath;
   }

   public String toString()
   {
      return this.number.getFace() + " of " + this.suit;

   }

   @Override public int compareTo( Card o )
   {
      return o.getNumber().getValue() - this.getNumber().getValue();
   }

   @Override public boolean equals( Object o )
   {
      return this.getNumber().getValue() == ( ( Card ) o ).getNumber().getValue() && this.getSuit()
                                                                                         .equals( ( ( Card ) o ).getSuit() );
   }
}
