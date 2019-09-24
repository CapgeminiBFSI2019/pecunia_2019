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
			throw new LoanException(ErrorConstants.FetchError);
		}
		finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
				
			} catch (SQLException sqlException) {
				// logger.error(sqlException.getMessage());
				throw new MyException(ErrorConstants.dbConnectionError);
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

			preparedStatement.setInt(1, loan.getLoanId());
			preparedStatement.setString(2, loan.getAccountId());
			preparedStatement.setDouble(3, loan.getAmount());
			preparedStatement.setString(4, loan.getType());
			preparedStatement.setDouble(5, loan.getTenure());
			preparedStatement.setDouble(6, loan.getRoi());
			preparedStatement.setString(7, loan.getLoanStatus());
			preparedStatement.setDouble(8, loan.getEmi());
			preparedStatement.setInt(9, loan.getCreditScore());
			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				// logger.error(sqlException.getMessage());
				throw new LoanException(ErrorConstants.LoanAddError);
			}

		} catch (SQLException sqlException) {
			// logger.error(sqlException.getMessage());
			throw new MyException(ErrorConstants.dbConnectionError);
		}

		finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				// logger.error(sqlException.getMessage());
				throw new MyException(ErrorConstants.dbConnectionError);
			}
		}

	}


}

	
	
	
	
