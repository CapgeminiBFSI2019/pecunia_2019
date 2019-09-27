package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;

public interface LoginDAO {
	public String validateEmail(Login login) throws MyException, LoginException;

	public String fetchPassword(Login login) throws MyException, LoginException;
}
