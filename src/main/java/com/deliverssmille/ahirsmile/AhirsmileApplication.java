package com.deliverssmille.ahirsmile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AhirsmileApplication {
	public static void main(String[] args) {

		SpringApplication.run(AhirsmileApplication.class, args);
	}
    
	
    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

}
