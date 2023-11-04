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
@Table(name="affirmations")
public class Affirmation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Affirmation cannot be blank.")
	@Size(min=2, max=325, message="Affirmation must be at least 2 characters long.")
	private String affirmationContent;
	
	//Relationships//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	@OneToMany(mappedBy="affirmation", fetch = FetchType.LAZY)
	private List<Comment> affirmationComments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "affirmationLikes", 
        joinColumns = @JoinColumn(name = "affirmation_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
    private List<User> userLikesOnAffirmation;
    
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Affirmation() {}
    
    /////GETTERS & SETTERS/////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAffirmationContent() {
		return affirmationContent;
	}

	public void setAffirmationContent(String affirmationContent) {
		this.affirmationContent = affirmationContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getAffirmationComments() {
		return affirmationComments;
	}

	public void setAffirmationComments(List<Comment> affirmationComments) {
		this.affirmationComments = affirmationComments;
	}

	public List<User> getUserLikesOnAffirmation() {
		return userLikesOnAffirmation;
	}

	public void setUserLikesOnAffirmation(List<User> userLikesOnAffirmation) {
		this.userLikesOnAffirmation = userLikesOnAffirmation;
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
