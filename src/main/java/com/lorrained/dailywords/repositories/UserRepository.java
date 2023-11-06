package com.lorrained.dailywords.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.lorrained.dailywords.models.User;


public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
}
