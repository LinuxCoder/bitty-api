package com.levoncorp.bitty;

import com.levoncorp.bitty.forecast.request.CurrencyApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(CurrencyApiProperties.class)
@EnableScheduling
public class BittyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BittyApplication.class, args);
	}

}
