package com.matthew.instagram.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matthew.instagram.Repository.UserRepository;
import com.matthew.instagram.entities.User;
import com.matthew.instagram.services.exception.DatabaseException;
import com.matthew.instagram.services.exception.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("ERROR! Objeto não encontrado com esse ID.", id));
	}

	public User insertUser(User user) {
		return userRepository.save(user);
	}

	public User atualizeUser(Integer id, User user) {
		try {
			User aux = userRepository.getReferenceById(id);
			atualize(aux, user);
			return userRepository.save(aux);
		} catch(EntityNotFoundException e) {
			throw new ObjectNotFoundException("ERROR! Objeto não encontrado com esse ID.", id);
		}
	}

	private void atualize(User aux, User user) {
		aux.setName(user.getName());
		aux.setEmail(user.getEmail());
	}

	public void deleteUser(Integer id) {
		try {
			userRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
