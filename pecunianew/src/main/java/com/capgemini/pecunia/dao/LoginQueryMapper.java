package com.capgemini.pecunia.dao;

public interface LoginQueryMapper {
	
	public static final String GET_SALT= "SELECT salt FROM login WHERE email =?";
	public static final String GET_PASSWORD = "SELECT password FROM login WHERE email=?";

}
