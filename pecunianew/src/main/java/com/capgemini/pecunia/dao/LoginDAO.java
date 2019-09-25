package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;

public interface LoginDAO {
	public String validateEmail(Login log) throws MyException, LoginException;
	public boolean validatePassword(Login log) throws MyException,LoginException;
}
