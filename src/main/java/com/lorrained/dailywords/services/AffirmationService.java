package com.lorrained.dailywords.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorrained.dailywords.models.Affirmation;
import com.lorrained.dailywords.models.User;
import com.lorrained.dailywords.repositories.AffirmationRepository;


@Service
public class AffirmationService {

	@Autowired
	private AffirmationRepository affirmationRepo;
	
    ///ALL
    public List<Affirmation> allAffirmations() {
        return affirmationRepo.findAll();
    }
    
    ///CREATE
    public Affirmation createAffirmation(Affirmation a) {
        return affirmationRepo.save(a);
    }
    
    ///UPDATE
    public Affirmation updateAffirmation(Affirmation a) {
        return affirmationRepo.save(a);
    }
    
    ///RETRIEVE SINGLE
    public Affirmation findAffirmation(Long id) {
        Optional<Affirmation> optionalAffirmation = affirmationRepo.findById(id);
        if(optionalAffirmation.isPresent()) {
            return optionalAffirmation.get();
        } else {
            return null;
        }
    }   
    
    
    
    ///RETRIEVE ALL USERS
    public List<Affirmation> allUsersAffirmations(Long id) {
        return affirmationRepo.findUserAffirmationsById(id);
    }
    
    
    
    
    ///DELETE
    public void deleteAffirmation (Long id) {
    	affirmationRepo.deleteById(id);
    }
	
    
    ///POPULATE LIKES
    public void likeAffirmation(Affirmation a, User u) {
    	List<User> userLikesOnAffirmation = a.getUserLikesOnAffirmation();
    	userLikesOnAffirmation.add(u);
    	affirmationRepo.save(a);
    	//adding likes
    }
    
    ///UNLIKE
    public void unlikeAffirmation(Affirmation a, User u) {
    	List<User> userLikesOnAffirmation = a.getUserLikesOnAffirmation();
    	userLikesOnAffirmation.remove(u);
    	affirmationRepo.save(a);
    	//removes likes
    }
}
