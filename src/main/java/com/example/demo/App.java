package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
////para ejecutar como jar
//public class App {
//
//	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
//	}
//
//}
////para ejecutar como war
public class App extends SpringBootServletInitializer{
    public static void main(String[] args) {
         SpringApplication.run(App.class, args);
  }
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	       return builder.sources(App.class);
	    
	}
}