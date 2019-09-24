package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;	
public class LoanDAOImpl implements LoanDAO {

	
	
	
	public void addLoanDetails(Loan loan) throws MyException, LoanException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		int queryResult = 0;
		try {
			preparedStatement = connection.prepareStatement(LoanQuerryMapper.Add_Loan);

			preparedStatement.setString(1, loan.getAccountId());
			preparedStatement.setDouble(2, loan.getAmount());
			preparedStatement.setInt(3, loan.getCreditScore());
			preparedStatement.setDouble(4, loan.getEmi());
			preparedStatement.setInt(5, loan.getLoanId());
			preparedStatement.setString(6, loan.getLoanStatus());
			preparedStatement.setDouble(7, loan.getRoi());
			preparedStatement.setDouble(8, loan.getTenure());
			preparedStatement.setString(9, loan.getType());

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

	
	
	
	
