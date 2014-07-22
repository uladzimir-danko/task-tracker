package net.danko.spring.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Users")
public class User {	

    @Id
    @Size(min=2, max=20)
    @Column(name = "USERNAME")
    private String username;
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String parameter) {
    	this.username = parameter;
    }

    @Size(min=5, max=20)
    @Column(name = "PASSWORD")
    private String password;
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String parameter) {
    	this.password = parameter;
    }
        
    @Column(name = "LANGUAGE")
    private String language;
    
    public String getLanguage() {
    	return language;
    }
    
    public void setLanguage(String parameter) {
    	this.language = parameter;
    }    

    @Column(name = "ENABLED")
    private Boolean enabled;
    
    public Boolean getEnabled() {
    	return enabled;
    }
    
    public void setEnabled(Boolean parameter) {
    	this.enabled = parameter;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade(CascadeType.DELETE)
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);  
    
    public Set<UserRole> getUserRoles() {
    	return userRoles;
    }
    
    public void setUserRoles(Set<UserRole> roles) {
    	this.userRoles = roles;
    }
}
