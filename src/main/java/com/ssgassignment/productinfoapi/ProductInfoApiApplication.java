package com.ssgassignment.productinfoapi;

import com.ssgassignment.productinfoapi.security.property.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties({JwtProperty.class})
@SpringBootApplication
public class ProductInfoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInfoApiApplication.class, args);
	}

}
