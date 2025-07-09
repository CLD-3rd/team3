package com.team3.fastpick.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.fastpick.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByIdIs(String id);

    Optional<User> findByIdIs(String id);

}
