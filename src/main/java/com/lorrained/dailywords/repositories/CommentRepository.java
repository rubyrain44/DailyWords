package com.lorrained.dailywords.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.dailywords.models.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAll();

}

