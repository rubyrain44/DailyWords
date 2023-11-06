package com.lorrained.dailywords.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorrained.dailywords.models.Fact;
import com.lorrained.dailywords.models.User;
import com.lorrained.dailywords.repositories.FactRepository;


@Service
public class FactService {

	@Autowired
	private FactRepository factRepo;
	
    ///ALL
    public List<Fact> allFacts() {
        return factRepo.findAll();
    }
    
    ///CREATE
    public Fact createFact(Fact f) {
        return factRepo.save(f);
    }
    
    ///UPDATE
    public Fact updateFact(Fact f) {
        return factRepo.save(f);
    }
    
    ///RETRIEVE SINGLE
    public Fact findFact(Long id) {
        Optional<Fact> optionalFact = factRepo.findById(id);
        if(optionalFact.isPresent()) {
            return optionalFact.get();
        } else {
            return null;
        }
    }   
    
    
    ///RETRIEVE ALL USERS
    public List<Fact> allUsersFacts(Long id) {
        return factRepo.findUserFactsById(id);
    }
       
    
    ///DELETE
    public void deleteFact (Long id) {
    	factRepo.deleteById(id);
    }
	
    
    ///POPULATE LIKES
    public void likeFact(Fact f, User u) {
    	List<User> userLikesOnFact = f.getUserLikesOnFact();
    	userLikesOnFact.add(u);
    	factRepo.save(f);
    	//adding likes
    }
    
    ///UNLIKE
    public void unlikeFact(Fact f, User u) {
    	List<User> userLikesOnFact = f.getUserLikesOnFact();
    	userLikesOnFact.remove(u);
    	factRepo.save(f);
    	//removes likes
    }
}
