package com.team3.fastpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.fastpick.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
