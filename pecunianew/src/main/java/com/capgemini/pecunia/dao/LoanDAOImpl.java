package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.DBConnection;	
public class LoanDAOImpl implements LoanDAO {

	Logger logger=Logger.getRootLogger();
	public LoanDAOImpl()
	{
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}

	/*******************************************************************************************************
	 * -Function Name : fetchAccountId(String accountId) - Input
	 * -Parameters : String accountId
	 * -Return Type : String 
	 * -Author : Rishabh Rai 
	 * -Creation Date : 24/09/2019
	 * -Description : Fetch account Id from Database 
	 * ********************************************************************************************************/

	
	public String fetchAccountId(String accountId) throws PecuniaException, LoanException {
		
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
			
			return Id;
		} catch (Exception e) {
			throw new LoanException(ErrorConstants.FETCH_ERROR);
		}
		finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
				
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new PecuniaException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		
		
	}
	
	/*******************************************************************************************************
	 * -Function Name : addLoanDetails(Loan loan) - Input
	 * -Parameters : Loan loan
	 * -Return Type : boolean 
	 * -Author : Rishabh Rai -
	 * -Creation Date : 24/09/2019
	 * -Description : Adding Loan details to database  
	 ********************************************************************************************************/
	public boolean addLoanDetails(Loan loan) throws PecuniaException, LoanException {
		Connection connection = DBConnection.getInstance().getConnection();
		boolean isadditionsuccess = false;
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
				throw new LoanException(ErrorConstants.LOAN_ADD_ERROR);
			}
			else
			{
				isadditionsuccess = true;
			}

		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage());
			throw new PecuniaException(sqlException.getMessage());
		}

		finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new PecuniaException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return isadditionsuccess;

	}


}