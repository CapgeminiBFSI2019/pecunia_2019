package com.capgemini.pecunia.service;


import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dao.LoginDAO;
import com.capgemini.pecunia.dao.LoginDAOImpl;
import com.capgemini.pecunia.dto.Login;

import com.capgemini.pecunia.exception.ErrorConstants;

import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Utility;

public class LoginServiceImpl implements LoginService{
	
	
	
	Logger logger = Logger.getRootLogger();
	public LoginServiceImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	
	
	LoginDAO loginDAO = new LoginDAOImpl();
	
	
	
	@Override
	public boolean validateEmail(Login login) throws MyException, LoginException {
		boolean flag=false;
		String pwd=null;
		String salt = loginDAO.validateEmail(login);
		if(salt==null) {
			logger.error("validation failed ");
			throw new LoginException(ErrorConstants.LOGIN_ERROR);
		}
		else {
			byte arr[] = null;
			try {
				arr = Utility.getSHA(login.getPassword() + "" + salt);
			} catch (NoSuchAlgorithmException e) {
				logger.error("validation failed ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
			String hashPassword = Utility.toHexString(arr);
			Login loginNew = new Login(login.getUsername(),null);
			try {
				pwd = loginDAO.fetchPassword(loginNew);
				if(pwd==hashPassword) {
					flag=true;
					logger.info("Login successful");
				}
			} catch (LoginException e) {
				logger.error("Validation failed ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
		}
		return flag;
	}
	
	
	
	

}
