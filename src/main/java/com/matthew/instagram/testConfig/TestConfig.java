package com.matthew.instagram.testConfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matthew.instagram.Repository.UserRepository;
import com.matthew.instagram.entities.User;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Matheus", "matheus@gmail.com");
		User u2 = new User(null, "Lucas", "lucas@gmail.com");
		User u3 = new User(null, "Gabriel", "gabriel@gmail.com");

		repository.saveAll(Arrays.asList(u1, u2, u3));
	}

}
