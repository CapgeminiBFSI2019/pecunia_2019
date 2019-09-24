package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.util.DBConnection;	
public class LoanDAOImpl implements LoanDAO {

	public void addLoanDetails(Loan loan) 
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		
		//String donorId=null;
		
		int queryResult=0;
		try
		{		
			preparedStatement=connection.prepareStatement(LoanQuerryMapper.Add_Loan);
			
			preparedStatement.setString(1,loan.getAccountId());
			preparedStatement.setDouble(2,loan.getAmount());			
			preparedStatement.setInt(3,loan.getCreditScore());
			preparedStatement.setDouble(4,loan.getEmi());
			preparedStatement.setInt(5,loan.getLoanId());	
			preparedStatement.setString(6,loan.getLoanStatus());	
			preparedStatement.setDouble(7,loan.getRoi());	
			preparedStatement.setDouble(8,loan.getTenure());	
			preparedStatement.setString(9,loan.getType());	
			
			queryResult=preparedStatement.executeUpdate();
		
		//	preparedStatement = connection.prepareStatement(LoanQuerryMapper.DONARID_QUERY_SEQUENCE);
		//	resultSet=preparedStatement.executeQuery();

			if(resultSet.next())
			{
				accountId=resultSet.getString(1);
						
			}
	
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new LoanException();

			}
			else
			{
				logger.info("Loan details added successfully:");
				return loan_Id;
			}

		}
		catch(SQLException sqlException)
		{
			logger.error(sqlException.getMessage());
			throw new LoanException();
		}

		finally
		{
			try 
			{
				//resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				logger.error(sqlException.getMessage());
				throw new LoanException();

			}
		}
		
		
	}

	@Override
	public void viewLoanDetails(Loan loan) {
		// TODO Auto-generated method stub
	}
	}