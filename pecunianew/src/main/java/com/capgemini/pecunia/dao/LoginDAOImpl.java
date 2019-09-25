package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.util.DBConnection;

public class LoginDAOImpl implements LoginDAO{

	Logger logger = Logger.getRootLogger();
	public LoginDAOImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}
	@Override
	public String validateEmail(Login login) throws MyException, LoginException {
		String salt=null;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;	
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_SALT);
			preparedStatement.setString(1,login.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			salt = resultSet.getString(3);
		}catch(SQLException e) {
		logger.error("login failed ");
		{throw new LoginException(ErrorConstants.LOGIN_ERROR);
			
		}
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			}catch(Exception e){
				logger.error("Connection Error ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
		}
		return salt;
	}

	@Override
	public boolean validatePassword(Login login) throws MyException, LoginException {
		boolean flag=false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;	
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_PASSWORD);
			preparedStatement.setString(1,login.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(login.getPassword()==resultSet.getString(2)) {
				flag=true;
				logger.info("login successful:");
			}
			else {
				logger.error("login failed ");
				throw new LoginException(ErrorConstants.LOGIN_ERROR);			}
		}catch(SQLException e) {throw new LoginException(ErrorConstants.LOGIN_ERROR);
		
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			}catch(Exception e){
				logger.error("connection error ");
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return flag;
	}

}
