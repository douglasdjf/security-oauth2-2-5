package com.sprincsecurity;

import com.sprincsecurity.security.util.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityOaut25Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOaut25Application.class, args);
	}

}
