package deepak.common;

public class Result
{
   private boolean isValid;

   private SetOfCards bestSetOfCards;

   public Result()
   {

   }

   public Result( boolean isValid, SetOfCards bestSetOfCards )
   {
      this.isValid = isValid;
      this.bestSetOfCards = bestSetOfCards;
   }

   public boolean isValid()
   {
      return isValid;
   }

   public void setValid( boolean isValid )
   {
      this.isValid = isValid;
   }

   public SetOfCards getBestSetOfCards()
   {
      return bestSetOfCards;
   }

   public void setBestSetOfCards( SetOfCards bestSetOfCards )
   {
      this.bestSetOfCards = bestSetOfCards;
   }

   public String toString()
   {
      return isValid ? "TRUE" : "FALSE" + " with cards " + "{" + this.bestSetOfCards + "}";
   }

}
