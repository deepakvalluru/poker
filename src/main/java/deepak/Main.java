package deepak;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.BestHand;
import deepak.pojo.Card;
import deepak.pojo.Player;
import deepak.type.Number;
import deepak.type.Suit;
import deepak.validator.PokerHandValidator;

public class Main {
	
	public static void main( String args[] )
	{
//	   testHighCard();
//	   testPair();
//	   testTwoPair();
//	   testThreeOfaKind();
//	   testStraight();
//	   testStraightWithA2345();
//	   testFlush();
//	   testFullHouse();
//	   testFourOfaKind();
//	   testStraightFlush();
//	   testRoyalFlush();
//	   testCasinoRoyaleGame();
//	   testHighCards();
	   testSraightCards();
	   
	}
	
	public static void testHighCards()
	{
	   Player player1 = new Player( "player one" );
      List< Card > playerCards1 = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.SPADES ) )
                                       .add( new Card( Number.TEN, Suit.DIAMONDS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player1.setPlayerCards( new SetOfCards( playerCards1 ) );
      
      Player player2 = new Player( "player Two" );
      List< Card > playerCards2 = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.CLUBS ) )
                                       .add( new Card( Number.TEN, Suit.CLUBS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player2.setPlayerCards( new SetOfCards( playerCards2 ) );
      
      Game highCardPoker = new Game( Stream.< Player > builder()
                                           .add( player1 )
                                           .add( player2 )
                                           .build()
                                           .collect( Collectors.toList() ) );
      
      highCardPoker.dealCard( new Card( Number.ACE, Suit.HEARTS ) );
      highCardPoker.dealCard( new Card( Number.EIGHT, Suit.SPADES ) );
      highCardPoker.dealCard( new Card( Number.SIX, Suit.HEARTS ) );
      highCardPoker.dealCard( new Card( Number.FOUR, Suit.SPADES ) );
      highCardPoker.dealCard( new Card( Number.JACK, Suit.SPADES ) );
      
      System.out.println( "Board Cards - " + highCardPoker.getBoardCards() + "\n" );
      GameResult gameResult = PokerEngine.getResult( highCardPoker );
      System.out.println( "Winners are - \n" + gameResult.getWinners() );
	}
	
	public static void testSraightCards()
   {
      Player player1 = new Player( "player one" );
      List< Card > playerCards1 = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.SPADES ) )
                                       .add( new Card( Number.TEN, Suit.DIAMONDS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player1.setPlayerCards( new SetOfCards( playerCards1 ) );
      
      Player player2 = new Player( "player Two" );
      List< Card > playerCards2 = Stream.< Card > builder()
                                       .add( new Card( Number.FIVE, Suit.CLUBS ) )
                                       .add( new Card( Number.TWO, Suit.CLUBS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player2.setPlayerCards( new SetOfCards( playerCards2 ) );
      
      Game straightCardsPoker = new Game( Stream.< Player > builder()
                                           .add( player1 )
                                           .add( player2 )
                                           .build()
                                           .collect( Collectors.toList() ) );
      
      straightCardsPoker.dealCard( new Card( Number.ACE, Suit.HEARTS ) );
      straightCardsPoker.dealCard( new Card( Number.QUEEN, Suit.SPADES ) );
      straightCardsPoker.dealCard( new Card( Number.THREE, Suit.HEARTS ) );
      straightCardsPoker.dealCard( new Card( Number.FOUR, Suit.SPADES ) );
      straightCardsPoker.dealCard( new Card( Number.JACK, Suit.SPADES ) );
      
      System.out.println( "Board Cards - " + straightCardsPoker.getBoardCards() + "\n" );
      GameResult gameResult = PokerEngine.getResult( straightCardsPoker );
      System.out.println( "Winners are - \n" + gameResult.getWinners() );
   }
   
	
   public static void testCasinoRoyaleGame()
   {
      // https://www.casino-games-online.biz/cards/poker/casino-royale-last-hand.html

      List< Player > players = Stream.< Player > builder()
                                     .add( getPlayerWithFlush() )
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

      System.out.println( "Board Cards - " + casinoRoyale.getBoardCards() + "\n" );
      GameResult gameResult = PokerEngine.getResult( casinoRoyale );
      System.out.println( "Winners are - \n" + gameResult.getWinners() );
   }
	
	// Japanese guy
   public static Player getPlayerWithFlush()
   {
      Player player = new Player( "Japanese Guy" );
      List< Card > playerCards = Stream.< Card > builder()
                                       .add( new Card( Number.KING, Suit.SPADES ) )
                                       .add( new Card( Number.QUEEN, Suit.SPADES ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }
   
   // Black Guy
   public static Player getPlayerWithSmallerFullHouse()
   {
      Player player = new Player( "Black Guy" );
      List< Card > playerCards = Stream.< Card > builder()
                                       .add( new Card( Number.EIGHT, Suit.CLUBS ) )
                                       .add( new Card( Number.EIGHT, Suit.DIAMONDS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }
   
   // The villain LeChiffre
   public static Player getPlayerWithBiggerFullHouse()
   {
      Player player = new Player( "LeChiffre" );
      List< Card > playerCards = Stream.< Card > builder()
                                       .add( new Card( Number.ACE, Suit.CLUBS ) )
                                       .add( new Card( Number.SIX, Suit.HEARTS ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }
	
   // The Hero - Bond....James Bond
   public static Player getPlayerWithStraightFlush()
   {
      Player player = new Player( "James Bond" );
      List< Card > playerCards = Stream.< Card > builder()
                                       .add( new Card( Number.SEVEN, Suit.SPADES ) )
                                       .add( new Card( Number.FIVE, Suit.SPADES ) )
                                       .build()
                                       .collect( Collectors.toList() );
      player.setPlayerCards( new SetOfCards( playerCards ) );
      return player;
   }
   
   public static void testHighCard()
	{
	   Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.FOUR, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.KING, Suit.SPADES );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
	}
	
	public static void testPair()
	{
	   Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.FOUR, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
	}
	
	public static void testTwoPair()
   {
	   Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testThreeOfaKind()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.ACE, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.KING, Suit.CLUBS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }

	public static void testStraight()
   {
      Card card1 = new Card( Number.EIGHT, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.NINE, Suit.HEARTS );
      Card card4 = new Card( Number.TEN, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card7 = new Card( Number.FIVE, Suit.CLUBS );
      
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testStraightWithA2345()
   {
      Card card1 = new Card( Number.EIGHT, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.KING, Suit.HEARTS );
      Card card4 = new Card( Number.FOUR, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card7 = new Card( Number.FIVE, Suit.CLUBS );
      Card card8 = new Card( Number.THREE, Suit.SPADES );
      Card card9 = new Card( Number.TWO, Suit.SPADES );
      
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      setOfCards.addCard( card9 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }

	
	public static void testFlush()
   {
      Card card1 = new Card( Number.EIGHT, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.KING, Suit.CLUBS );
      Card card4 = new Card( Number.FOUR, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card7 = new Card( Number.FIVE, Suit.CLUBS );
      Card card8 = new Card( Number.TWO, Suit.SPADES );
      
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testFullHouse()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.JACK, Suit.SPADES );
      Card card5 = new Card( Number.JACK, Suit.CLUBS );
      Card card6 = new Card( Number.TWO, Suit.SPADES );
      Card card7 = new Card( Number.TWO, Suit.CLUBS );
      Card card8 = new Card( Number.TWO, Suit.HEARTS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testFourOfaKind()
   {
      Card card1 = new Card( Number.FIVE, Suit.CLUBS );
      Card card2 = new Card( Number.ACE, Suit.DIAMONDS );
      Card card3 = new Card( Number.ACE, Suit.HEARTS );
      Card card4 = new Card( Number.ACE, Suit.SPADES );
      Card card5 = new Card( Number.ACE, Suit.CLUBS );
      Card card6 = new Card( Number.KING, Suit.SPADES );
      Card card7 = new Card( Number.TWO, Suit.CLUBS );
      Card card8 = new Card( Number.TWO, Suit.HEARTS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testStraightFlush()
   {
      Card card1 = new Card( Number.TWO, Suit.CLUBS );
      Card card2 = new Card( Number.THREE, Suit.CLUBS );
      Card card3 = new Card( Number.FOUR, Suit.CLUBS );
      Card card4 = new Card( Number.FIVE, Suit.CLUBS );
      Card card5 = new Card( Number.SIX, Suit.CLUBS );
      Card card6 = new Card( Number.NINE, Suit.CLUBS );
      Card card7 = new Card( Number.TEN, Suit.CLUBS );
      Card card8 = new Card( Number.JACK, Suit.CLUBS );
      Card card9 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card10 = new Card( Number.KING, Suit.CLUBS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      setOfCards.addCard( card9 );
      setOfCards.addCard( card10 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }
	
	public static void testRoyalFlush()
   {
      Card card1 = new Card( Number.TWO, Suit.CLUBS );
      Card card2 = new Card( Number.THREE, Suit.CLUBS );
      Card card3 = new Card( Number.FOUR, Suit.CLUBS );
      Card card4 = new Card( Number.FIVE, Suit.CLUBS );
      Card card5 = new Card( Number.SIX, Suit.CLUBS );
      Card card6 = new Card( Number.NINE, Suit.CLUBS );
      Card card7 = new Card( Number.TEN, Suit.CLUBS );
      Card card8 = new Card( Number.JACK, Suit.CLUBS );
      Card card9 = new Card( Number.QUEEN, Suit.CLUBS );
      Card card10 = new Card( Number.KING, Suit.CLUBS );
      Card card11 = new Card( Number.ACE, Suit.CLUBS );
      
      SetOfCards setOfCards = new SetOfCards();
      setOfCards.addCard( card1 );
      setOfCards.addCard( card2 );
      setOfCards.addCard( card3 );
      setOfCards.addCard( card4 );
      setOfCards.addCard( card5 );
      setOfCards.addCard( card6 );
      setOfCards.addCard( card7 );
      setOfCards.addCard( card8 );
      setOfCards.addCard( card9 );
      setOfCards.addCard( card10 );
      setOfCards.addCard( card11 );
      
      System.out.println( setOfCards );
      
      BestHand bestHand = PokerHandValidator.getBestHand(setOfCards);
      System.out.println( bestHand );
   }

}
