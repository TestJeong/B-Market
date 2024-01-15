package com.side.bmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmarketApplication.class, args);
	}

}
