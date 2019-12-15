package deepak;

import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.pojo.Player;
import deepak.type.Number;
import deepak.type.Suit;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{

   public static void main( String args[] )
   {
      Instant start = Instant.now();
      Game casinoRoyale = getCasinoRoyaleGame();
      System.out.println( "Board Cards - " + casinoRoyale.getBoardCards() );
      GameResult gameResult = PokerEngine.getResult( casinoRoyale );
      System.out.println( "Winners are - \n " + gameResult.getWinners() );
      Instant finish = Instant.now();
      System.out.println( "Time taken to process - " + Duration.between( start, finish ).toMillis() );
   }

   public static Game getCasinoRoyaleGame()
   {
      // https://www.casino-games-online.biz/cards/poker/casino-royale-last-hand.html

      List<Player> players = Stream.<Player>builder().add( getPlayerWithFlush() )
                                                     .add( getPlayerWithSmallerFullHouse() )
                                                     .add( getPlayerWithBiggerFullHouse() )
                                                     .add( getPlayerWithStraightFlush() )
                                                     .build()
                                                     .collect( Collectors.toList() );

      Game casinoRoyale = new Game( players );

      casinoRoyale.dealCard( new Card( Number.ACE, Suit.HEARTS ) );
      casinoRoyale.dealCard( new Card( Number.EIGHT, Suit.SPADES ) );
      casinoRoyale.dealCard( new Card( Number.SIX, Suit.SPADES ) );
      casinoRoyale.dealCard( new Card( Number.FOUR, Suit.SPADES ) );
      casinoRoyale.dealCard( new Card( Number.ACE, Suit.SPADES ) );

      return casinoRoyale;
   }

   // Japanese guy
   private static Player getPlayerWithFlush()
   {
      Player player = new Player( "Japanese Guy" );
      List<Card> playerCards = Stream.<Card>builder().add( new Card( Number.KING, Suit.SPADES ) )
                                                     .add( new Card( Number.QUEEN, Suit.SPADES ) )
                                                     .build()
                                                     .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }

   // Black Guy
   private static Player getPlayerWithSmallerFullHouse()
   {
      Player player = new Player( "Black Guy" );
      List<Card> playerCards = Stream.<Card>builder().add( new Card( Number.EIGHT, Suit.CLUBS ) )
                                                     .add( new Card( Number.EIGHT, Suit.DIAMONDS ) )
                                                     .build()
                                                     .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }

   // The villain LeChiffre
   private static Player getPlayerWithBiggerFullHouse()
   {
      Player player = new Player( "LeChiffre" );
      List<Card> playerCards = Stream.<Card>builder().add( new Card( Number.ACE, Suit.CLUBS ) )
                                                     .add( new Card( Number.SIX, Suit.HEARTS ) )
                                                     .build()
                                                     .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }

   // The Hero - Bond....James Bond
   private static Player getPlayerWithStraightFlush()
   {
      Player player = new Player( "James Bond" );
      List<Card> playerCards = Stream.<Card>builder().add( new Card( Number.SEVEN, Suit.SPADES ) )
                                                     .add( new Card( Number.FIVE, Suit.SPADES ) )
                                                     .build()
                                                     .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }

}
