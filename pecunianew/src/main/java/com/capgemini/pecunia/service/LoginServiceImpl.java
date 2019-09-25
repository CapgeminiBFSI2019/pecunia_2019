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

	public boolean validateEmail(Login log) throws MyException ,LoginException  {

	public boolean validateEmail(Login log) throws MyException, LoginException {

		boolean flag=false;
		String salt = loginDAO.validateEmail(log);
		if(salt==null) {
			logger.error("validation failed ");
			throw new LoginException(ErrorConstants.LOGIN_ERROR);
		}
		else {
			byte arr[] = null;
			try {
				arr = Utility.getSHA(log.getPassword() + "" + salt);
			} catch (NoSuchAlgorithmException e) {
				logger.error("validation failed ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
			String hashPassword = Utility.toHexString(arr);
			Login logNew = new Login(log.getUsername(),hashPassword,"");
			try {
				flag = loginDAO.validatePassword(logNew);
			} catch (LoginException e) {
				logger.error("Validation failed ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
		}
		return flag;
	}
	

}
