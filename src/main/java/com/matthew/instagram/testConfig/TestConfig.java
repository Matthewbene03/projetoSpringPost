package com.matthew.instagram.testConfig;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matthew.instagram.Repository.PostRepository;
import com.matthew.instagram.Repository.UserRepository;
import com.matthew.instagram.entities.Post;
import com.matthew.instagram.entities.User;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	@Autowired
	private PostRepository postRepository;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post p1 = new Post(null, sdf.parse("06/10/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post p2 = new Post(null, sdf.parse("06/10/2024"), "Bom dia", "Acordei feliz hoje!", maria);


		repository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(p1, p2));
	}

}
