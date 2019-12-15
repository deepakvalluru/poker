package deepak;

import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deepak.common.GameResult;

public class PercentageCalculatorTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );
   
   @Test
   public void test()
   {
      Game casinoRoyale = GameTest.getCasinoRoyaleGame();
      GameResult result = PercentageCalculator.calculatePercentages( casinoRoyale );

      logger.info( "-------------------------------------Here are the results--------------------------------" );
      result.getPlayers().forEach( p -> logger.info( "{} -----------> {} %", p.getName(),  String.format( "%.2f", p.getPercentage() ) ) );
   }
}
