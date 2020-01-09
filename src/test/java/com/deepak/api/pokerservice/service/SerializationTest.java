package com.deepak.api.pokerservice.service;

import com.deepak.api.pokerservice.model.Number;
import com.deepak.api.pokerservice.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class SerializationTest
{
   private final static Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

   @Test
   public void testSerializationAndDeserialization() throws IOException
   {
      Player player = new Player( "player1", 1 );
      CardDeal cardDeal = new CardDeal();
      Game game = new Game( Arrays.asList( player ) );
      Card dealtCard = new Card( Number.FOUR, Suit.SPADES );
      cardDeal.setGame( game );
      cardDeal.setDealtCard( dealtCard );
      cardDeal.setBoardCard( true );
      cardDeal.setPlayerCard( false );
      cardDeal.setPlayer( null );

      ObjectMapper objectMapper = new ObjectMapper();

      String json = objectMapper.writeValueAsString( game );
      logger.info( json );

      Game game1 = objectMapper.readValue( json, Game.class );
      logger.info( game1.toString() );

      json = objectMapper.writeValueAsString( cardDeal );
      logger.info( json );

      CardDeal cardDeal1 = objectMapper.readValue( json, CardDeal.class );
      logger.info( cardDeal1.toString() );

   }
}
