package com.axxes.whosit;

import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.repository.StaffRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = "com.axxes.whosit")
@EnableAutoConfiguration
public class WhositApplication {

	public static void main(String[] args){
		SpringApplication.run(WhositApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StaffRepository personRepository) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Staff>> typeReference = new TypeReference<List<Staff>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/person.json");
			try {
				List<Staff> users = mapper.readValue(inputStream,typeReference);
				List<Staff> goodStaffUsers = users.stream()
                        .filter(user -> user.getLastName() != null && user.getFirstName() != null)
                        .filter(user -> !user.getLastName().toLowerCase().contains("test") && !user.getFirstName().toLowerCase().contains("test"))
                        .collect(Collectors.toList());
				personRepository.saveAll(goodStaffUsers);
				System.out.println("Users Saved!");
			} catch (IOException e){
				e.printStackTrace();
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}

}
