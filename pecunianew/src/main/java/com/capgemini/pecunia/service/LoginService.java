package com.capgemini.pecunia.service;

import java.security.NoSuchAlgorithmException;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;

public interface LoginService {
	public boolean validateEmail(Login log) throws MyException, LoginException;
}
