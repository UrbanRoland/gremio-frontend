package com.gremio.service;


import com.gremio.model.User;
import com.gremio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService   {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		//this.roleRepository = roleRepository;
	}
	

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User fetchUserByEmailAndPassword(String email,String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}	
	
}
