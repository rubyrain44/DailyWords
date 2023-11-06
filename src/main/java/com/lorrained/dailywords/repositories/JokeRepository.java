package com.lorrained.dailywords.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.dailywords.models.Joke;


public interface JokeRepository extends CrudRepository<Joke, Long> {

	List<Joke> findAll();
	
	List<Joke> findUserJokesById(Long id);
	
}
