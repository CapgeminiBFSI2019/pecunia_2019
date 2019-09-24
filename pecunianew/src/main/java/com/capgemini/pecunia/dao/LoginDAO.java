package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.MyException;

public interface LoginDAO {
	public String validateEmail(Login log) throws MyException;
	public boolean validatePassword(Login log) throws MyException;
}
