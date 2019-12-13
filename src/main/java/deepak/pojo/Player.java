package deepak.pojo;

import deepak.common.SetOfCards;

import java.util.ArrayList;
import java.util.Objects;

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

   public static Player clonePlayer( Player player)
   {
      Player clone = new Player( player.getName() );
      clone.setPlayerCards( new SetOfCards( new ArrayList<>( player.playerCards.getCards() ) ) );
      clone.setBoardCards( new SetOfCards( new ArrayList<>( player.boardCards.getCards() ) )  );
      return clone;
   }

   @Override public boolean equals( Object o )
   {
      if( this == o )
         return true;
      if( o == null || getClass() != o.getClass() )
         return false;
      Player player = ( Player ) o;
      return name.equals( player.name );
   }

   @Override public int hashCode()
   {
      return Objects.hash( name );
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
