package com.capgemini.pecunia.service;


import java.security.NoSuchAlgorithmException;

import com.capgemini.pecunia.dao.LoginDAO;
import com.capgemini.pecunia.dao.LoginDAOImpl;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Utility;

public class LoginServiceImpl implements LoginService{

	LoginDAO loginDAO = new LoginDAOImpl();
	@Override
	public boolean validateEmail(Login login) throws MyException, LoginException {
		boolean flag=false;
		String pwd=null;
		String salt = loginDAO.validateEmail(login);
		if(salt==null) {
			throw new LoginException(ErrorConstants.LOGIN_ERROR);
		}
		else {
			byte arr[] = null;
			try {
				arr = Utility.getSHA(login.getPassword() + "" + salt);
			} catch (NoSuchAlgorithmException e) {
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
			String hashPassword = Utility.toHexString(arr);
			Login loginNew = new Login(login.getUsername(),"","");
			try {
				pwd = loginDAO.fetchPassword(loginNew);
				if(pwd==hashPassword) {
					flag=true;
				}
			} catch (LoginException e) {
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
		}
		return flag;
	}
	
	
	
	

}
