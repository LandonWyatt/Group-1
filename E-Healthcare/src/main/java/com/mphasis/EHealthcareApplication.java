package com.mphasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.mphasis"})
@EntityScan(basePackages="com.mphasis.entity")
public class EHealthcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(EHealthcareApplication.class, args);
	}

}
