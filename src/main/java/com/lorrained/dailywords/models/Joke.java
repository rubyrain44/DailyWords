package com.lorrained.dailywords.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="jokes")
public class Joke {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Joke cannot be blank.")
	@Size(min=2, max=325, message="Joke must be at least 2 characters long.")
	private String jokeContent;
	
	//Relationships//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	@OneToMany(mappedBy="joke", fetch = FetchType.LAZY)
	private List<Comment> jokeComments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "jokeLikes", 
        joinColumns = @JoinColumn(name = "joke_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
    private List<User> userLikesOnJoke;
    
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Joke() {}

    /////GETTERS & SETTERS/////

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJokeContent() {
		return jokeContent;
	}

	public void setJokeContent(String jokeContent) {
		this.jokeContent = jokeContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getJokeComments() {
		return jokeComments;
	}

	public void setJokeComments(List<Comment> jokeComments) {
		this.jokeComments = jokeComments;
	}

	public List<User> getUserLikesOnJoke() {
		return userLikesOnJoke;
	}

	public void setUserLikesOnJoke(List<User> userLikesOnJoke) {
		this.userLikesOnJoke = userLikesOnJoke;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	/////PRE/////
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }    
    
}
