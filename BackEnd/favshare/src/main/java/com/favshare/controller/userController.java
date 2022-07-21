package com.favshare.controller;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.entity.userEntity;
import com.favshare.repo.Repo;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/test")
public class userController {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;
	@Autowired
	private Repo repo;
	
	@GetMapping("/searchAll")
	@Transactional() // 필요한지, 작동이 잘 되는지는 아직 모름
	public String searchAll() {
		System.out.println("helloworld");
		System.out.println(repo.findAll().toString());
		return repo.findAll().toString();
	}
	
	@GetMapping("/search")
	@Transactional()
	public userEntity search(String id) {
		userEntity s = repo.search(id);
		return s;
	}
	
	// Create
	@GetMapping("/insert") // @PostMapping으로 바꿔야 함
	@Transactional()
	public void insert() { // 나중에는 변수 받아서 넣어줘야겠지?
		repo.save(new userEntity("qwerty1434@naver.com", "name", LocalDateTime.now(),  LocalDateTime.now()));
	}
	
	// Delete
	@GetMapping("/delete")
	@Transactional()
	public void delete(String id) {
		id = "qwerty1434@naver.com";
		repo.deleteById(id);
	}
	
	// Update
	@GetMapping("/update") // POST, PUT으로 변경
	@Transactional()
	public void update(String id, String email, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {

	}
}