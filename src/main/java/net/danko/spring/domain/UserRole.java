package net.danko.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserRoles")
public class UserRole {
	
	@Id
    @Column(name = "USER_ROLE_ID")
    @GeneratedValue
    private Integer user_role_id;
	
	public Integer getUser_role_id() {
    	return user_role_id;
    }
	
	public void setUser_role_id(Integer id) {
    	this.user_role_id = id;
    }

    @Column(name = "ROLE")
    private String role;
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME", nullable = false)
    private User user;
    
    public User getUser() {
		return this.user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
}
