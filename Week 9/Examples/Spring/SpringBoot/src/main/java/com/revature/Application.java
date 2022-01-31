package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.annotation.JankAnnotation;

/*
 * Remember that the @SpringBootApplication annotation is a convenience
 * annotation that is a combination of: @Configuration, @ComponentScan,
 * @EnableAutoConfiguration (this allows Spring to automatically configure
 * and add beans to our IOC container).
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
