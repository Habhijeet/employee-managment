package com.spring.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.user_service.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
