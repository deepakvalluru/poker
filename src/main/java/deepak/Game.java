package deepak;

import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.pojo.Deck;
import deepak.pojo.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game
{
   private List<Player> players;
   private SetOfCards   boardCards;
   private Deck         deck;

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

   public void dealCard( Card card )
   {
      getDeck().getCards().remove( card );
      getBoardCards().addCard( card );
      getPlayers().forEach( x -> x.getBoardCards().addCard( card ) );
   }

   public static Game cloneGame( Game game )
   {
      List<Player> players =
               game.getPlayers().stream().map( p -> Player.clonePlayer( p ) ).collect( Collectors.toList() );
      Game cloneGame = new Game( players );
      cloneGame.setBoardCards( new SetOfCards( new ArrayList<>( game.getBoardCards().getCards() ) ) );
      return cloneGame;
   }

}
