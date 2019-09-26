package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;	
public class LoanDAOImpl implements LoanDAO {

	@Override
	public String fetchAccountId(String accountId) throws MyException, LoanException {
		
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String Id = null;
		try {
			preparedStatement = connection.prepareStatement(LoanQuerryMapper.FETCH_ACCOUNT_ID);
			preparedStatement.setString(1, accountId);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Id=rs.getString(1);
			}
			
			
		} catch (SQLException e) {
			throw new LoanException(ErrorConstants.FETCH_ERROR);
		}
		finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
				
			} catch (SQLException sqlException) {
				// logger.error(sqlException.getMessage());
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return Id;
		
	}
	
	
	
	
	public void addLoanDetails(Loan loan) throws MyException, LoanException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		int queryResult = 0;
		try {
			preparedStatement = connection.prepareStatement(LoanQuerryMapper.ADD_LOAN_DETAILS);

			preparedStatement.setString(1, loan.getAccountId());
			preparedStatement.setDouble(2, loan.getAmount());
			preparedStatement.setString(3, loan.getType());
			preparedStatement.setDouble(4, loan.getTenure());
			preparedStatement.setDouble(5, loan.getRoi());
			preparedStatement.setString(6, loan.getLoanStatus());
			preparedStatement.setDouble(7, loan.getEmi());
			preparedStatement.setInt(8, loan.getCreditScore());
			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				// logger.error(sqlException.getMessage());
				throw new LoanException(ErrorConstants.LOAN_ADD_ERROR);
			}

		} catch (SQLException sqlException) {
			// logger.error(sqlException.getMessage());
			throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);
		}

		finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				// logger.error(sqlException.getMessage());
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}

	}


}