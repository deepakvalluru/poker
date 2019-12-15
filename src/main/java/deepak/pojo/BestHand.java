package deepak.pojo;

import deepak.common.SetOfCards;
import deepak.type.Hand;

public class BestHand
{
   private Hand       hand;
   private SetOfCards setOfCards;

   public BestHand()
   {

   }

   public BestHand( Hand hand, SetOfCards setOfCards )
   {
      this.hand = hand;
      this.setOfCards = setOfCards;
   }

   public Hand getHand()
   {
      return hand;
   }

   public void setHand( Hand hand )
   {
      this.hand = hand;
   }

   public SetOfCards getSetOfCards()
   {
      return setOfCards;
   }

   public void setSetOfCards( SetOfCards setOfCards )
   {
      this.setOfCards = setOfCards;
   }

   public String toString()
   {
      return this.hand.toString() + " with Cards " + "{ " + this.setOfCards.toString() + " }";
   }
}
