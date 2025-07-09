package com.team3.fastpick.service;

import org.springframework.stereotype.Service;

import com.team3.fastpick.entity.User;
import com.team3.fastpick.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
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

}
