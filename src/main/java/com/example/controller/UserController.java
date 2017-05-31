package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userservice;

	@Autowired
	public UserController(UserService userservice) {
		this.userservice = userservice;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> findItems() {
		return userservice.findall();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findone(@PathVariable Integer id) {
		try {
			return userservice.findone(id);
		} catch (Exception e) {
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public User addItem(@RequestBody User user) {
		user.setId(null);

		return userservice.create(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public User updateItem(@RequestBody User user, @PathVariable Integer id) {
		User u = userservice.findone(id);
		if (u == null)
			return null;
		user.setId(id);
		return userservice.update(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable Integer id) {
		try {
			userservice.delete(id);
		} catch (Exception e) {
		}
	}
}
