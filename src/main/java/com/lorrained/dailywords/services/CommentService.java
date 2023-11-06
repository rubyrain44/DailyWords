package com.lorrained.dailywords.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorrained.dailywords.models.Comment;
import com.lorrained.dailywords.repositories.CommentRepository;


@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
    ///ALL COMMENTS
    public List<Comment> allComments() {
        return commentRepo.findAll();
    }
    
    ///CREATE COMMENT
    public Comment createComment(Comment c) {
        return commentRepo.save(c);
    }
    
    ///UPDATE COMMENT
    public Comment updateComment(Comment c) {
        return commentRepo.save(c);
    }
    
    ///RETRIEVE SINGLE COMMENT
    public Comment findComment(Long id) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if(optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            return null;
        }
    }
    
    ///DELETE COMMENT
    public void deleteComment (Long id) {
    	commentRepo.deleteById(id);
    }
	
}