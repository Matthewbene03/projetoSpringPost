package com.matthew.instagram.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matthew.instagram.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{}
