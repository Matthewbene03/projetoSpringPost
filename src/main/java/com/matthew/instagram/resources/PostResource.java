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
import com.matthew.instagram.services.PostService;


@RestController
@RequestMapping(value = "/post")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> listPost = postService.findAll();
		return ResponseEntity.ok().body(listPost);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable Integer id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@PostMapping
	public ResponseEntity<Post> insertPost(@RequestBody Post post){
		post = postService.insertPost(post);
		return ResponseEntity.ok().body(post);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> atualizePost(@PathVariable Integer id, @RequestBody Post post) {
		post = postService.atualizePost(id, post);
		return ResponseEntity.ok().body(post);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Integer id){
		postService.deletePost(id);
		return ResponseEntity.noContent().build();
	}
}
