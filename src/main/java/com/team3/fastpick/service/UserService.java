package com.team3.fastpick.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.team3.fastpick.entity.User;
import com.team3.fastpick.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	public void signup(User user) {
		userRepository.save(user);
		// TODO Auto-generated method stub
		
	}

	public boolean isIdDuplicate(String id) {
        return userRepository.findByIdIs(id).isPresent();

	}


	public User login(String id, String password) {
		return userRepository.findByIdAndPassword(id, password).orElse(null);
	}
	
}
