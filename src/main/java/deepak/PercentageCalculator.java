package deepak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.util.CombinatoricsUtils;

import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.pojo.Deck;
import deepak.pojo.Player;

public class PercentageCalculator
{
   public static void calculatePercentages( Game game )
   {
      List< List< Card > > remainingCardCombinations = getCombinations( game.getDeck(), 5-game.getBoardCards().getCards().size() );
      Map< Player, Integer > playerWinMap = new HashMap<>();
      List< Card > currentBoardCards = game.getBoardCards().getCards();
      for( List< Card > list : remainingCardCombinations )
      {
         SetOfCards boardCards = new SetOfCards( new ArrayList<>( currentBoardCards ) );
         boardCards.addCards( list );
         System.out.println( " CARDS ON THE BOARD - " + boardCards );
         for( Player player : game.getPlayers() )
         {
            player.setBoardCards( boardCards );
         }
         GameResult result = PokerEngine.getResult( game );
         if( result.getIsResultAvailable() )
         {
            result.getWinners().forEach( x -> playerWinMap.merge( x, 1, ( y, z ) -> y + z ) );
         }
      }
      playerWinMap.keySet().forEach( x -> System.out.println( x.getName() + " - " + playerWinMap.get( x ) ) );
   }

   public static List< List< Card > > getCombinations( Deck deck, int cardsToDeal )
   {
      List< Card > cards = deck.getCards();
      int cardsLeftInDeck = cards.size();
      Iterator< int[] > c = CombinatoricsUtils.combinationsIterator( cardsLeftInDeck, cardsToDeal );
      List< List< Card > > list = new ArrayList<>();
      c.forEachRemaining( x -> {
         list.add( IntStream.of( x ).boxed().map( y -> cards.get( y ) ).collect( Collectors.toList() ) );
      } );
      list.forEach( x -> System.out.println( x ) );
      System.out.println( "size - " + list.size() );
      return list;
   }
}
