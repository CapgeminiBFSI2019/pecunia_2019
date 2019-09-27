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
import com.capgemini.pecunia.util.DBConnection;

public class LoginDAOImpl implements LoginDAO {

	Logger logger = Logger.getRootLogger();

	public LoginDAOImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	@Override
	public String validateEmail(Login login) throws MyException, LoginException {
		String salt = null;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_SALT);
			preparedStatement.setString(1, login.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				salt = resultSet.getString("salt");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new LoginException(ErrorConstants.LOGIN_ERROR);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LoginException(ErrorConstants.LOGIN_ERROR);
			}
		}
		return salt;
	}

	@Override
	public String fetchPassword(Login login) throws MyException, LoginException {
		Connection connection = null;
		String password = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(LoginQueryMapper.GET_PASSWORD);
			preparedStatement.setString(1, login.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString("password");
			}
			return password;
		} catch (SQLException e) {
			throw new LoginException(ErrorConstants.LOGIN_ERROR);
		} finally {
			try {
				connection.close();
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new LoginException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
	}

}
