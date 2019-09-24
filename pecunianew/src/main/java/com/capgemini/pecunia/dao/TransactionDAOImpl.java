package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getBalance(Account account) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String accountId = account.getId();
		double balance = -1;

		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.GET_ACCOUNT_BALANCE_QUERY);

			preparedStatement.setString(1, accountId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				balance = resultSet.getDouble("balance");
			}

			if (balance == -1) {
				throw new TransactionException("Balance retrieve fail");
			}

		} catch (TransactionException me) {
			// logger here
			throw new TransactionException(me.getMessage());
		} catch (Exception e) {
			// add logger here
			throw new MyException(e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new MyException("Error in closing db connection");
			}

		}
		return balance;
	}

	@Override
	public boolean updateBalance(Account account) throws MyException, TransactionException {
		boolean flag = false;
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		String accountId = account.getId();
		int rowsAffected = 0;
		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.UPDATE_ACOCUNT_BALANCE_QUERY);
			preparedStatement.setString(1, accountId);
			rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0) {
				flag = true;
			} else {
				// logger here
				throw new TransactionException("Update balance failed");
			}
		} catch (TransactionException te) {
			// logger here
			throw new TransactionException(te.getMessage());
		} catch (Exception e) {
			// logger here
			throw new MyException(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// logger here
				throw new MyException("Error closing db connection");
			}
		}
		return flag;
	}
	
	@Override
	public int creditUsingSlip(Transaction transaction) throws MyException, TransactionException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String accId=transaction.getAccountId();
        double newBalance=transaction.getClosingBalance();
        Date transDate=transaction.getTransDate();
        java.sql.Date sqltransactionDate = new java.sql.Date(transDate.getTime());
        Account acc=new Account();
        acc.setId(accId);
        boolean flag=updateBalance(acc);
        if(flag) {
        	try {
            
            preparedStatement = connection.prepareStatement(TransactionQueryMapper.UPDATE_ACOCUNT_BALANCE_QUERY);
            preparedStatement.setString(1,accId);
            preparedStatement.setDate(2, sqltransactionDate);      
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e) {
			throw new MyException(ErrorConstants.dbConnectionError); 
		
    }finally {
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            // logger here
            throw new MyException(ErrorConstants.dbConnectionError);
        }
    }
       return  	
 }
        
        
	}


