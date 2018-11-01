package deepak.pojo;

import deepak.common.SetOfCards;

public class Player implements Comparable< Player >
{
   private String name;
   
   private SetOfCards playerCards;
   
   private SetOfCards boardCards;
   
   private BestHand bestHand;
   
   private Float percentage;

   public Player( String name )
   {
      this.name = name;
   }
   
   public Player( SetOfCards playerCards )
   {
      this.playerCards = playerCards;
   }
   
   public Player( SetOfCards playerCards, SetOfCards boardCards )
   {
      this.playerCards = playerCards;
      this.boardCards = boardCards;
   }
   
   public String getName()
   {
      return name;
   }

   public void setName( String name )
   {
      this.name = name;
   }

   public SetOfCards getPlayerCards()
   {
      return playerCards;
   }

   public void setPlayerCards( SetOfCards playerCards )
   {
      this.playerCards = playerCards;
   }

   public SetOfCards getBoardCards()
   {
      if( this.boardCards == null )
      {
         this.boardCards = new SetOfCards();
      }
      
      return this.boardCards;
   }

   public void setBoardCards( SetOfCards boardCards )
   {
      this.boardCards = boardCards;
   }

   public BestHand getBestHand()
   {
      return bestHand;
   }

   public void setBestHand( BestHand bestHand )
   {
      this.bestHand = bestHand;
   }

   public Float getPercentage()
   {
      return percentage;
   }

   public void setPercentage( Float percentage )
   {
      this.percentage = percentage;
   }

   public String toString()
   {
      return "-------------------------------------------------------------------"
             + "-----------------------------------------------------------------"
             + "\n"
             + "Player Name: "
             + name
             + "\n"
             + "Player Cards: "
             + playerCards
             + "\n"
             + "Player Best Hand is "
             + bestHand;
   }

   @Override
   public int compareTo( Player o )
   {  
      return o.getBestHand().getHand().getValue() - this.getBestHand().getHand().getValue();
   }
   
}
