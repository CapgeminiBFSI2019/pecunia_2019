package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getbalance(Account account) throws MyException, TransactionException {
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
	
	
	public int debitusingCheque(Transaction transaction, Cheque cheque) {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String accId=transaction.getAccountId();
		double newBalance=transaction.getClosingBalance();
		Date transDate=transaction.getTransDate();
		java.sql.Date sqltransactionDate = new java.sql.Date(transDate.getTime());
		Account acc=new Account(accId, Values.NA, Values.NA, Values.NA, Values.NA, newBalance, Value.NA, Value.NA);
		try {
        boolean flag=updateBalance(acc);
		if(flag) {
			//Cheque clearedCheque=new Cheque(Values.NA, cheque.getNum(), accId, cheque.getHolderName(), Value.BANK_NAME, cheque.getIfsc(), cheque.getIssueDate(), Values.CHEQUE_STATUS_CLEARED);
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_CHEQUE_QUERY);
			preparedStatement.setInt(1,cheque.getNum());
			preparedStatement.setString(2,accId);
			preparedStatement.setString(3,cheque.getHolderName());
			preparedStatement.setString(4,Values.BANK_NAME);
			preparedStatement.setString(5,cheque.getIfsc());
			preparedStatement.setDate(6, sqltransactionDate);
			preparedStatement.setString(7, Values.CHEQUE_STATUS_CLEARED);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.GET_CHEQUE_ID_QUERY);
			preparedStatement.setString(1, accId);
			resultSet = preparedStatement.executeQuery();
			String chequeId=resultSet.getString("cheque_id");
		}
		else {
			
		}
		return 0;
	}finally {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// logger here
			throw new MyException("Error closing db connection");
		}
	}

}
}