package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private UserRepository userrepository;

	@Autowired
	public UserServiceImpl(UserRepository usererepository) {
		this.userrepository = usererepository;
	}

	public UserServiceImpl() {
	}

	public List<User> findall() {
		return userrepository.findAll();
	}

	public User create(User user) {
		return userrepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userrepository.saveAndFlush(user);
	}

	public User findone(Integer id) {
		return userrepository.findOne(id);
	}

	public void delete(Integer id) {
		userrepository.delete(id);
	}

}
