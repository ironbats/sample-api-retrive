package br.com.amaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.amaro.controller",
		"br.com.amaro.service"})
public class AmaroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmaroApplication.class, args);
	}

}
