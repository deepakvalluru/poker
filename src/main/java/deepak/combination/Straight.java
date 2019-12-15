package deepak.combination;

import deepak.common.Result;
import deepak.common.SetOfCards;
import deepak.pojo.Card;
import deepak.type.Number;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

;

public class Straight
{
   public static Result isValid( SetOfCards setOfCards )
   {
      Map<Integer, List<Card>> cardMap =
               setOfCards.getCards().stream().collect( Collectors.groupingBy( x -> x.getNumber().getValue() ) );

      if( cardMap.containsKey( Number.ACE.getValue() ) )
      {
         cardMap.put( 1, cardMap.get( Number.ACE.getValue() ) );
      }

      List<Integer> list =
               cardMap.keySet().stream().sorted( Collections.reverseOrder() ).collect( Collectors.toList() );

      int highCardStraight = 0;

      if( list.size() >= 5 )
      {
         for( int i = 0; i < list.size() - 4; i++ )
         {
            int count = 1;
            boolean isStraight = false;
            for( int j = i; j < list.size(); j++ )
            {
               if( list.get( j ) - 1 == list.get( j + 1 ) )
               {
                  count++;
                  if( count == 5 )
                  {
                     isStraight = true;
                     break;
                  }
               }
               else
               {
                  break;
               }
            }
            if( isStraight )
            {
               highCardStraight = list.get( i );
               break;
            }
         }
      }

      if( highCardStraight != 0 )
      {
         final int highCardStraightNumber = highCardStraight;
         List<Integer> fiveStraightNumbers = cardMap.keySet()
                                                    .stream()
                                                    .sorted( Collections.reverseOrder() )
                                                    .filter( x -> x <= highCardStraightNumber )
                                                    .limit( 5 )
                                                    .collect( Collectors.toList() );
         List<Card> fiveStraightCards =
                  fiveStraightNumbers.stream().map( x -> cardMap.get( x ).get( 0 ) ).collect( Collectors.toList() );
         SetOfCards cards = new SetOfCards( fiveStraightCards );
         return new Result( true, cards );
      }

      return new Result( false, null );
   }
}
