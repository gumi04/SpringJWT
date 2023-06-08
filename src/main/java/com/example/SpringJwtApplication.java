package com.example;

import com.example.constants.ERole;
import com.example.models.dto.UserDto;
import com.example.models.entity.RoleEntity;
import com.example.models.entity.UserEntity;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SpringJwtApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return  args -> {
			UserDto admin = new UserDto();
			admin.setUsername("any1");
			admin.setPassword("1234");
			admin.setEmail("any1@example.com");
			admin.setRoles(Set.of("ADMIN"));


			UserDto user = new UserDto();
			user.setUsername("any2");
			user.setPassword("1234");
			user.setEmail("any2@example.com");
			user.setRoles(Set.of("USER"));

			UserDto invitado = new UserDto();
			invitado.setUsername("any3");
			invitado.setPassword("1234");
			invitado.setEmail("any3@example.com");
			invitado.setRoles(Set.of("INVITED"));
			userService.saveUser(admin);
			userService.saveUser(invitado);
			userService.saveUser(user);
		};
	}

}
