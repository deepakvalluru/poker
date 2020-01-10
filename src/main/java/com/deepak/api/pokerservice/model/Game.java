package com.deepak.api.pokerservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game
{
   private List<Player> players;
   private SetOfCards   boardCards;
   private Deck         deck;

   public Game()
   {

   }

   public Game( List<Player> players )
   {
      setDeck( new Deck() );
      setPlayers( players );
      this.boardCards = new SetOfCards();
      players.forEach( x -> x.getPlayerCards().getCards().forEach( y -> getDeck().getCards().remove( y ) ) );
   }

   public List<Player> getPlayers()
   {
      return players;
   }

   public void setPlayers( List<Player> players )
   {
      this.players = players;
   }

   public void addPlayer( Player player )
   {
      getPlayers().add( player );
   }

   public SetOfCards getBoardCards()
   {
      return boardCards;
   }

   public void setBoardCards( SetOfCards boardCards )
   {
      this.boardCards = boardCards;
   }

   public Deck getDeck()
   {
      return deck;
   }

   public void setDeck( Deck deck )
   {
      this.deck = deck;
   }

   public void dealBoardCard( Card card )
   {
      getDeck().getCards().remove( card );
      getBoardCards().addCard( card );
      getPlayers().forEach( x -> x.getBoardCards().addCard( card ) );
   }

   public void dealBoardCardBacktoDeck( Card card )
   {
      getDeck().getCards().add( card );
      getBoardCards().removeCard( card );
      getPlayers().forEach( x -> x.getBoardCards().removeCard( card ) );
   }

   public void dealPlayerCard( Card card, Player player )
   {
      getDeck().getCards().remove( card );
      // get existing player cards for the player from the game object
      // in case parameter player does not have player cards passed in it.
      SetOfCards playerCards = getPlayers().stream()
                                          .filter( p -> p.equals( player ) )
                                          .map( Player::getPlayerCards )
                                          .collect( Collectors.toList() )
                                          .get( 0 );
      playerCards.addCard( card );
      getPlayers().stream().filter( p ->  p.equals( player ) ).forEach( p ->
                                                                        {
                                                                           p.setPlayerCards( playerCards );
                                                                           if( playerCards.size() >=2 )
                                                                           {
                                                                              p.setActive( true );
                                                                           }
                                                                        } );
   }

   public void dealPlayerCardBackToDeck( Card card, Player player )
   {
      getDeck().getCards().add( card );
      // get existing player cards for the player from the game object
      // in case parameter player does not have player cards passed in it.
      SetOfCards playerCards = getPlayers().stream()
                                           .filter( p -> p.equals( player ) )
                                           .map( Player::getPlayerCards )
                                           .collect( Collectors.toList() )
                                           .get( 0 );
      playerCards.removeCard( card );
      getPlayers().stream().filter( p ->  p.equals( player ) ).forEach( p ->
                                                                        {
                                                                           p.setPlayerCards( playerCards );
                                                                           if( playerCards.size() >=2 )
                                                                           {
                                                                              p.setActive( true );
                                                                           }
                                                                           else
                                                                           {
                                                                              p.setActive( false );
                                                                           }
                                                                        } );
   }

   public static Game cloneGame( Game game )
   {
      List<Player> players =
               game.getPlayers().stream().filter( Player::isActive ).map( p -> Player.clonePlayer( p ) ).collect( Collectors.toList() );
      Game cloneGame = new Game( players );
      cloneGame.setBoardCards( new SetOfCards( new ArrayList<>( game.getBoardCards().getCards() ) ) );
      return cloneGame;
   }

}
