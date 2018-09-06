package com.axxes.whosit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.axxes.whosit")
@EnableAutoConfiguration
public class WhositApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhositApplication.class, args);
	}
}
