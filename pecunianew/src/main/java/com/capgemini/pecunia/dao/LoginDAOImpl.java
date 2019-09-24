package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class LoginDAOImpl implements LoginDAO{


	@Override
	public String validateEmail(Login log) throws MyException {
		String salt=null;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;	
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_SALT);
			preparedStatement.setString(1,log.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			salt = resultSet.getString(3);
		}catch(SQLException e) {
			//handle
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			}catch(Exception e){
				//error in closing connection
			}
		}
		return salt;
	}

	@Override
	public boolean validatePassword(Login log) throws MyException {
		boolean flag=false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;	
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_PASSWORD);
			preparedStatement.setString(1,log.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(log.getPassword()==resultSet.getString(2)) {
				flag=true;
			}
			else {
				//incorrect password
			}
		}catch(SQLException e) {
			//handle
		}
		finally {
			try {
				connection.close();
				preparedStatement.close();
			}catch(Exception e){
				//error in closing connection
			}
		}
		return flag;
	}

}
