package com.lorrained.dailywords.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorrained.dailywords.models.Joke;
import com.lorrained.dailywords.models.User;
import com.lorrained.dailywords.repositories.JokeRepository;


@Service
public class JokeService {

	@Autowired
	private JokeRepository jokeRepo;
	
    ///ALL
    public List<Joke> allJokes() {
        return jokeRepo.findAll();
    }
    
    ///CREATE
    public Joke createFact(Joke j) {
        return jokeRepo.save(j);
    }
    
    ///UPDATE
    public Joke updateJoke(Joke j) {
        return jokeRepo.save(j);
    }
    
    ///RETRIEVE SINGLE
    public Joke findJoke(Long id) {
        Optional<Joke> optionalJoke = jokeRepo.findById(id);
        if(optionalJoke.isPresent()) {
            return optionalJoke.get();
        } else {
            return null;
        }
    }   
    
    
    ///RETRIEVE ALL USERS
    public List<Joke> allUsersJokes(Long id) {
        return jokeRepo.findUserJokesById(id);
    }
       
    
    ///DELETE
    public void deleteJoke (Long id) {
    	jokeRepo.deleteById(id);
    }
	
    
    ///POPULATE LIKES
    public void likeJoke(Joke j, User u) {
    	List<User> userLikesOnJoke = j.getUserLikesOnJoke();
    	userLikesOnJoke.add(u);
    	jokeRepo.save(j);
    	//adding likes
    }
    
    ///UNLIKE
    public void unlikeJoke(Joke j, User u) {
    	List<User> userLikesOnJoke = j.getUserLikesOnJoke();
    	userLikesOnJoke.remove(u);
    	jokeRepo.save(j);
    	//removes likes
    }
}