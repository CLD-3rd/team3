package com.team3.fastpick.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.fastpick.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByIdAndPassword(String id, String password);
  boolean existsByIdIs(String id);

  Optional<User> findByIdIs(String id);

}
