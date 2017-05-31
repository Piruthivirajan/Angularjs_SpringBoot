package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {

	List<User> findall();

	User create(User user);
	
	User update(User user);

	User findone(Integer id);

	void delete(Integer id);
	
	
}
