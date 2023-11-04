package com.lorrained.dailywords.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.dailywords.models.Fact;


public interface FactRepository extends CrudRepository<Fact, Long> {

	List<Fact> findAll();
	
}
