package com.capgemini.pecunia.service;


import java.security.NoSuchAlgorithmException;

import com.capgemini.pecunia.dao.LoginDAO;
import com.capgemini.pecunia.dao.LoginDAOImpl;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Utility;

public class LoginServiceImpl implements LoginService{

	LoginDAO loginDAO = new LoginDAOImpl();
	@Override
	public boolean validateEmail(Login log) throws MyException ,LoginException  {
		boolean flag=false;
		String salt = loginDAO.validateEmail(log);
		if(salt==null) {
			//Account not found (exception)
		}
		else {
			byte arr[] = null;
			try {
				arr = Utility.getSHA(log.getPassword() + "" + salt);
			} catch (NoSuchAlgorithmException e) {
				//technical error
			}
			String hashPassword = Utility.toHexString(arr);
			Login logNew = new Login(log.getUsername(),hashPassword,"");
			flag = loginDAO.validatePassword(logNew);
		}
		return flag;
	}
	

}
