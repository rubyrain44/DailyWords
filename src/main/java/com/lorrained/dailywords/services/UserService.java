package com.lorrained.dailywords.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.lorrained.dailywords.models.LoginUser;
import com.lorrained.dailywords.models.User;
import com.lorrained.dailywords.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
    /////REGISTER USER METHOD/////
    public User register(User u, BindingResult result) {
    	if (!u.getConfirm().equals(u.getPassword())) {
    		result.rejectValue("confirm", null, "Passwords must match.");
    	}
    	if (result.hasErrors()) {
    		return null;
    	}
    	String hashPW = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
    	u.setPassword(hashPW);
        return userRepo.save(u);
    }
 
    
    /////LOGIN USER METHOD/////
    public User login(LoginUser l, BindingResult result) {
    	Optional<User> optUser = userRepo.findByEmail(l.getEmail());
    	if (optUser.isEmpty()) {
    		return null;    		
    	}
    	User user = optUser.get();
    	if (!BCrypt.checkpw(l.getPassword(), user.getPassword())) {
    		return null;
    	}
    	return user;
    }
    
    
    /////FIND USER/////
    public User findById(Long id) {
    	Optional<User> optUser = userRepo.findById(id);
    	if (optUser.isEmpty()) {
    		return null;
    	}
    	return optUser.get();
    }
}