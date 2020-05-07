package com.fit5136.missionToMars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class MissionToMarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissionToMarsApplication.class, args);
	}

}
