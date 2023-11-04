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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Username is required!")
	@Size(min=3, max=30, message="Username must be at least 3 characters!")
	private String username;
	
	@NotEmpty(message="Email is required!")
	@Email(message="Please provide a valid email!")
	private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, max=128, message="Password must be at least 8 characters!")
	private String password;	
	
	@NotEmpty(message="Confirmation password is required!")
	@Size(min=8, max=128, message="Confirm Password must be at least 8 characters!")
	private String confirm;
	
	//Relationships//
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Affirmation> affirmations;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Joke> jokes;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Fact> facts;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Comment> comments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "affirmationLikes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "affirmation_id")
    )
    private List<Affirmation> userAffirmationLikes;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "jokeLikes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "joke_id")
    )
    private List<Joke> userJokeLikes;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "factLikes", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "fact_id")
    )
    private List<Fact> userFactLikes;
	
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
  
    public User() {}

    /////GETTERS & SETTERS/////

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Affirmation> getAffirmations() {
		return affirmations;
	}

	public void setAffirmations(List<Affirmation> affirmations) {
		this.affirmations = affirmations;
	}

	public List<Joke> getJokes() {
		return jokes;
	}

	public void setJokes(List<Joke> jokes) {
		this.jokes = jokes;
	}

	public List<Fact> getFacts() {
		return facts;
	}

	public void setFacts(List<Fact> facts) {
		this.facts = facts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Affirmation> getUserAffirmationLikes() {
		return userAffirmationLikes;
	}

	public void setUserAffirmationLikes(List<Affirmation> userAffirmationLikes) {
		this.userAffirmationLikes = userAffirmationLikes;
	}

	public List<Joke> getUserJokeLikes() {
		return userJokeLikes;
	}

	public void setUserJokeLikes(List<Joke> userJokeLikes) {
		this.userJokeLikes = userJokeLikes;
	}

	public List<Fact> getUserFactLikes() {
		return userFactLikes;
	}

	public void setUserFactLikes(List<Fact> userFactLikes) {
		this.userFactLikes = userFactLikes;
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

	