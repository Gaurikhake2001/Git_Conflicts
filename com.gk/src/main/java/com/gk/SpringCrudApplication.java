package com.gk;

//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringCrudApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
		System.out.println("Application started");
		System.out.println("hi am huda mla place kra ");
	}

}
