package deepak.pojo;

import java.util.ArrayList;
import java.util.List;

import deepak.type.Number;
import deepak.type.Suit;

public final class Deck 
{
	private List< Card > cards = getAllCards();

	public Deck()
	{
		
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	private List<Card> getAllCards( )
	{
		cards = new ArrayList<>();
		for( Number number : Number.values() )
		{
			for( Suit suit : Suit.values() )
			{
				Card card = new Card( number, suit );
				cards.add(card);
			}
		}
		
//		cards.forEach( x -> System.out.println( x.toString() ) ); 
		return cards;
	}
	
	public int dealCard( Card card )
	{
	   cards.remove( card );
	   return cards.size();
	}
}
