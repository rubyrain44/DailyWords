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
@Table(name="facts")
public class Fact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Fact cannot be blank.")
	@Size(min=2, max=325, message="Fact must be at least 2 characters long.")
	private String factContent;
	
	//Relationships//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	@OneToMany(mappedBy="fact", fetch = FetchType.LAZY)
	private List<Comment> factComments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "factLikes", 
        joinColumns = @JoinColumn(name = "fact_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
    private List<User> userLikesOnFact;
    
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Fact() {}
    
    /////GETTERS & SETTERS/////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFactContent() {
		return factContent;
	}

	public void setFactContent(String factContent) {
		this.factContent = factContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getFactComments() {
		return factComments;
	}

	public void setFactComments(List<Comment> factComments) {
		this.factComments = factComments;
	}

	public List<User> getUserLikesOnFact() {
		return userLikesOnFact;
	}

	public void setUserLikesOnFact(List<User> userLikesOnFact) {
		this.userLikesOnFact = userLikesOnFact;
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