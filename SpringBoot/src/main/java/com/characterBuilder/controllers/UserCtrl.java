package com.characterBuilder.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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

import com.characterBuilder.entities.User;
import com.characterBuilder.services.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.EmailAlreadyRegistered;

@RestController
@RequestMapping("user")
public class UserCtrl {

	@Autowired
	private UserSrvc userSrvc;

	public UserCtrl(UserSrvc userSrvc) {
		super();
		this.userSrvc = userSrvc;
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user, HttpSession session) throws EmailAlreadyRegistered {
		userSrvc.add(user);
		return ResponseEntity.ok(null);
	}

	@DeleteMapping
  public ResponseEntity<?> deleteUser(@RequestBody User user, HttpSession session) {
		userSrvc.delete(user);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody User user, HttpSession session) {
      //TODO: AOP login varification.
			userSrvc.update(user);
			return ResponseEntity.ok(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<User>> getUser(@PathVariable long id, HttpSession session) {
		List<User> user = new ArrayList<User>();
		user.add(userSrvc.getById(id));
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all")
	public ResponseEntity<Set<User>> getAllUsers(HttpSession session) {
		return ResponseEntity.ok(userSrvc.getAll());
	}
}
