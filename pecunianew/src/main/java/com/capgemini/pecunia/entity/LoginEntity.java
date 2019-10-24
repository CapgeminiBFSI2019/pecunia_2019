package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

	@Entity
	@NamedQueries({
		@NamedQuery(name = "LoginEntity.getsecret_keyByusername", query = "FROM LoginEntity where Username =:email") })

	@Table(name = "login")
	public class LoginEntity {

		@Id
		@Column(name = "email")
		private String username;
		@Column(name = "password")
		private String password;
		
	

public LoginEntity() {
	
}



public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}


}



