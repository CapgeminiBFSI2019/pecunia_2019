package com.capgemini.pecunia.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Constants;
import com.capgemini.pecunia.util.DBConnection;

public class LoanDisbursalDAOImpl implements LoanDisbursalDAO {

	public double amountToBePaid(double emi, int tenure) {
		return emi * tenure;
	}

	public List<Loan> retrieveLoanList() throws IOException, MyException {

		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Loan> requestList = new ArrayList<Loan>();

		try {
			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_LOAN);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int loanId = resultSet.getInt("loan_id");
				String account_id = resultSet.getString("account_id");
				Double amount = resultSet.getDouble("amount");
				String type = resultSet.getString("type");
				int tenure = resultSet.getInt("tenure");
				int roi = resultSet.getInt("roi");
				String status = resultSet.getString("loan_status");
				Double emi = resultSet.getDouble("emi");
				int creditScore = resultSet.getInt("credit_score");
				Loan loan = new Loan(loanId, account_id, amount, type, tenure, roi, status, emi, creditScore);
				requestList.add(loan);

			}
		}

		catch (SQLException sqlException) {
			throw new MyException(Constants.CONNECTION_FAILURE);
		}

		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new MyException(Constants.FILE_CLOSING_FAILURE);

			}
		}

		return requestList;

	}



	public void releaseLoanSheet(List<Loan> loanList) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			for (int i = 0; i < loanList.size(); i++) {
				double amountDue = amountToBePaid(loanList.get(1).getEmi(), loanList.get(1).getTenure());
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.INSERT_QUERY);
				preparedStatement.setInt(1, loanList.get(i).getLoanId());
				preparedStatement.setString(2, loanList.get(i).getAccountId());
				preparedStatement.setDouble(3, loanList.get(i).getAmount());
				preparedStatement.setDouble(4, amountDue);
				preparedStatement.setInt(5, loanList.get(i).getTenure());
				preparedStatement.execute();
			}

		} catch (SQLException sqlException) {
			throw new MyException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException(Constants.FILE_CLOSING_FAILURE);

			}
		}

	}

	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		try {
			preparedStatement = connection
					.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_APPROVED_LOAN);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int loanDisbursedId = resultSet.getInt("loan_disbursal_id");
				int loanId = resultSet.getInt("loan_id");
				String accountId = resultSet.getString("account_id");
				Double disbursedAmount = resultSet.getDouble("disbursed_amount");
				double dueAmount = resultSet.getDouble("due_amount");
				double emiToBePaid = resultSet.getDouble("emi_to_be_paid");
				LoanDisbursal getDetails = new LoanDisbursal(loanDisbursedId, loanId, accountId, disbursedAmount,
						dueAmount, emiToBePaid);
				approvedLoanList.add(getDetails);

			}
		} catch (SQLException sqlException) {
			throw new MyException(Constants.CONNECTION_FAILURE);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException(Constants.FILE_CLOSING_FAILURE);

			}
		}

		return approvedLoanList;
	}

	public void updateLoanAccount(ArrayList<LoanDisbursal> loanApprovals, double dueAmount,double tenure, String accountId) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			
			
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.UPDATE_LOAN_ACCOUNT);
				preparedStatement.setDouble(1, dueAmount);
				preparedStatement.setDouble(2, tenure);
				preparedStatement.setString(3, accountId);
				preparedStatement.execute();
				
			

		} catch (SQLException sqlException) {
			throw new MyException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException(Constants.FILE_CLOSING_FAILURE);

			}
		}
		
		

}
	
	public void updateStatus(ArrayList<Loan> loanRequests, String accountId, String Status) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			
			
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.UPDATE_LOAN_STATUS);
				preparedStatement.setString(1, Status);
				preparedStatement.setString(2, accountId);
				preparedStatement.execute();
				
			

		} catch (SQLException sqlException) {
			throw new MyException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException(Constants.FILE_CLOSING_FAILURE);

			}
		}
}
}
