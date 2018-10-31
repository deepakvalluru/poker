package deepak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import deepak.common.GameResult;
import deepak.common.SetOfCards;
import deepak.pojo.BestHand;
import deepak.pojo.Player;
import deepak.type.Hand;
import deepak.validator.PokerHandValidator;

public class PokerEngine
{
   public static GameResult getResult( Game game )
   {
      GameResult gameResult = new GameResult();
      gameResult.setPlayers( game.getPlayers() );
      
      for( Player player : gameResult.getPlayers() )
      {
         SetOfCards totalSet = new SetOfCards();
         totalSet.addCards( player.getBoardCards().getCards() );
         totalSet.addCards( player.getPlayerCards().getCards() );
         BestHand bestHand = PokerHandValidator.getBestHand( totalSet );
         player.setBestHand( bestHand );
         System.out.println( player );
      }
      
      Collections.sort( gameResult.getPlayers() );
      
      Map< Hand, List< Player > > playerMap = gameResult.getPlayers()
                                                        .stream()
                                                        .collect( Collectors.groupingBy( x -> x.getBestHand()
                                                                                               .getHand() ) );
      
      Optional< Hand > winningHand = playerMap.keySet().stream().sorted( ( x, y ) -> y.getValue() - x.getValue() ).findFirst();
      
      List< Player > winningHandPlayers = playerMap.get( winningHand.get() );
      
      List< Player > winningPlayers = getWinningPlayers( winningHandPlayers );
      
      BestHand bestHand = gameResult.getPlayers().get( 0 ).getBestHand();
      
      gameResult.setWinners( winningPlayers );
      gameResult.setBestHand( bestHand );
      gameResult.setIsResultAvailable( true );
      
      return gameResult;
   }

   
   private static List< Player > getWinningPlayers( List< Player > players )
   {

      if( players == null || players.size() == 1 )
      {
         return players;
      }
      
      Map< Double, List< Player > > map = new HashMap<>();

      Double max = Double.MIN_VALUE;
      for( Player p : players )
      {
         List< Integer > numbers = p.getBestHand()
                                    .getSetOfCards()
                                    .getCards()
                                    .stream()
                                    .map( x -> x.getNumber().getValue() )
                                    .collect( Collectors.toList() );

         double total = 0;
         for( int i = 4; i >= 0; i-- )
         {
            total = total + numbers.get( 4 - i ) * Math.pow( 10, i );
         }
         if( total >= max )
         {
            max = total;
         }
         if( map.get( total ) == null )
         {
            map.put( total, new ArrayList<>() );
         }
         map.get( total ).add( p );
      }

      return map.get( max );
   }

}
