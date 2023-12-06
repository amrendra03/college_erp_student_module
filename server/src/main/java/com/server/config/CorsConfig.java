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

      // Allow all origins, headers, and methods. You may want to customize this based
      // on your requirements.
      config.addAllowedOrigin("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");

      // Allow credentials. Set this to true if your API and client are on different
      // domains and you need to include credentials (e.g., cookies).
      config.setAllowCredentials(true);

      source.registerCorsConfiguration("/**", config);

      return new CorsFilter(source);
   }
}
