package com.matthew.instagram.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matthew.instagram.Repository.PostRepository;
import com.matthew.instagram.entities.Post;
import com.matthew.instagram.services.exception.DatabaseException;
import com.matthew.instagram.services.exception.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(Integer id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("ERROR! Objeto não encontrado com esse ID.", id));
	}

	public Post insertPost(Post post) {
		return postRepository.save(post);
	}

	public Post atualizePost(Integer id, Post post) {
		try {
			Post aux = postRepository.getReferenceById(id);
			atualize(aux, post);
			return postRepository.save(aux);
		} catch(EntityNotFoundException e) {
			throw new ObjectNotFoundException("ERROR! Objeto não encontrado com esse ID.", id);
		}
	}

	private void atualize(Post aux, Post post) {
		aux.setTitle(post.getTitle());
		aux.setBody(post.getBody());
		aux.setDate(post.getDate());
	}

	public void deletePost(Integer id) {
		try {
			postRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
