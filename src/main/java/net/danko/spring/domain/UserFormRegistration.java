package net.danko.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@Entity
public class UserFormRegistration {	

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

    @Column(name = "PASSWORDCONFIRM")
    private String passwordConfirm;
    
    public String getPasswordConfirm() {
    	return this.passwordConfirm;
    }
    
    public void setPasswordConfirm(String parameter) {
    	this.passwordConfirm = parameter;
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
    
    private boolean valid;
    
    public void setValid(Boolean parameter) { }
    
    public Boolean getValid() {
    	return this.valid;
    }
    
    @AssertTrue
    private boolean isValid() {
    	this.valid = this.password.equals(this.passwordConfirm);
      return this.valid;
    }
}
