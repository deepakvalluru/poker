package com.deepak.api.pokerservice.model;

import java.util.ArrayList;
import java.util.List;

public class GameResult
{
   private List<Player> players;
   private List<Player> winners;
   private BestHand     bestHand;
   private Boolean      isResultAvailable;
   private Game         game;

   public List<Player> getPlayers()
   {
      return players;
   }

   public void setPlayers( List<Player> players )
   {
      this.players = players;
   }

   public List<Player> getWinners()
   {
      if( winners == null )
      {
         winners = new ArrayList<>();
      }
      return winners;
   }

   public void setWinners( List<Player> winners )
   {
      this.winners = winners;
   }

   public void addWinner( Player player )
   {
      getWinners().add( player );
   }

   public BestHand getBestHand()
   {
      return bestHand;
   }

   public void setBestHand( BestHand bestHand )
   {
      this.bestHand = bestHand;
   }

   public Boolean getIsResultAvailable()
   {
      return isResultAvailable;
   }

   public void setIsResultAvailable( Boolean isResultAvailable )
   {
      this.isResultAvailable = isResultAvailable;
   }

   public Game getGame()
   {
      return game;
   }

   public void setGame( Game game )
   {
      this.game = game;
   }
}
