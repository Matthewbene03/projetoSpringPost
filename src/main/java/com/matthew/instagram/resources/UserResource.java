package com.matthew.instagram.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matthew.instagram.entities.Post;
import com.matthew.instagram.entities.User;
import com.matthew.instagram.services.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> listUser = userService.findAll();
		return ResponseEntity.ok().body(listUser);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPostsUser(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getListPost());
	}
	
	@PostMapping
	public ResponseEntity<User> insertUser(@RequestBody User user){
		user = userService.insertUser(user);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> atualizeUser(@PathVariable Integer id, @RequestBody User user) {
		user = userService.atualizeUser(id, user);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
