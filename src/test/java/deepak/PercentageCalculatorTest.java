package deepak;

import org.junit.Test;

public class PercentageCalculatorTest
{
   
   @Test
   public void test()
   {
      Game casinoRoyale = GameTest.getCasinoRoyaleGame();
      PercentageCalculator.calculatePercentages( casinoRoyale );
      
   }
}
