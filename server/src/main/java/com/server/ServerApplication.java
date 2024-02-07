package com.server;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {
	private static  final Logger LOG = LoggerFactory.getLogger(ServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		LOG.info("Server Started......");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
