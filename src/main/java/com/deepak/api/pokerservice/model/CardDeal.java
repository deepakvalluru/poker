package com.deepak.api.pokerservice.model;

import javax.validation.constraints.NotNull;

public class CardDeal
{
   @NotNull
   private Game game;
   private Card dealtCard;
   private boolean playerCard;
   private boolean boardCard;
   private Player player;

   public CardDeal()
   {
   }

   public Game getGame()
   {
      return game;
   }

   public void setGame( Game game )
   {
      this.game = game;
   }

   public Card getDealtCard()
   {
      return dealtCard;
   }

   public void setDealtCard( Card dealtCard )
   {
      this.dealtCard = dealtCard;
   }

   public boolean isPlayerCard()
   {
      return playerCard;
   }

   public void setPlayerCard( boolean playerCard )
   {
      this.playerCard = playerCard;
   }

   public boolean isBoardCard()
   {
      return boardCard;
   }

   public void setBoardCard( boolean boardCard )
   {
      this.boardCard = boardCard;
   }

   public Player getPlayer()
   {
      return player;
   }

   public void setPlayer( Player player )
   {
      this.player = player;
   }
}
