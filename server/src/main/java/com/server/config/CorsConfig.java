package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();

      // allow all origins, headers, and methods. You may want to customize this based
      // on your requirements.
      config.addAllowedOriginPattern("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");

      config.setAllowCredentials(true);

      source.registerCorsConfiguration("/**", config);

      return new CorsFilter(source);
   }
}
