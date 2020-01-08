package com.deepak.api.pokerservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PokerServiceApplication implements WebMvcConfigurer
{
   public static void main(String[] args) {
      SpringApplication.run( PokerServiceApplication.class, args);
   }

   @Override
   public void addResourceHandlers( ResourceHandlerRegistry registry) {

      // Register resource handler for images
      registry.addResourceHandler("/api/pokergame/images/**").addResourceLocations("classpath:/images/")
              .setCacheControl( CacheControl.maxAge( 2, TimeUnit.HOURS).cachePublic());
   }

   @Bean
   public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
      MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      objectMapper.configure( DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
      objectMapper.configure( DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
      objectMapper.configure( DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
      jsonConverter.setObjectMapper(objectMapper);
      return jsonConverter;
   }

}
