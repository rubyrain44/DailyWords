package com.lorrained.dailywords.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.dailywords.models.Affirmation;


public interface AffirmationRepository extends CrudRepository<Affirmation, Long> {

	
	List<Affirmation> findAll();
	
	List<Affirmation> findUserAffirmationsById(Long id);
	
}
