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
import com.capgemini.pecunia.util.DBConnection;

public class LoanDisbursalDAOImpl implements LoanDisbursalDAO {

	public double amountToBePaid(double emi, int tenure) {
		return emi * tenure;
	}

	public List<Loan> retrieveLoanList() throws IOException, MyException {

		Connection connection = DBConnection.getInstance().getConnection();
		int loanRequests = 0;
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
			throw new MyException("Tehnical problem occured. Refer log");
		}

		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new MyException("Files cannot be closed");

			}
		}

		return requestList;

	}

	public void releaseLoanSheet(List<Loan> loanList) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			for (int i = 0; i < loanList.size(); i++) {
				double amountDue = amountToBePaid(loanList.get(i).getEmi(), loanList.get(i).getTenure());
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.INSERT_QUERY);

				preparedStatement.setInt(1, loanList.get(i).getLoanId());
				preparedStatement.setString(2, loanList.get(i).getAccountId());
				preparedStatement.setDouble(3, loanList.get(i).getAmount());
				preparedStatement.setDouble(4, amountDue);
				preparedStatement.setInt(5, loanList.get(i).getTenure());
			}
		} catch (SQLException sqlException) {
			throw new MyException(sqlException.getMessage());
//				throw new MyException("Tehnical problem occured. Refer log");
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException("Files cannot be closed");

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
				LoanDisbursal loanDisbursal = new LoanDisbursal();
				loanDisbursal.setLoanDisbursalId(resultSet.getInt(1));
				loanDisbursal.setLoanId(resultSet.getInt(2));
				loanDisbursal.setAccountId(resultSet.getString(3));
				loanDisbursal.setDisbursedAmount(resultSet.getDouble(4));
				loanDisbursal.setDueAmount(resultSet.getDouble(5));
				loanDisbursal.setNumberOfEmiToBePaid(resultSet.getDouble(6));
				approvedLoanList.add(loanDisbursal);

			}
		} catch (SQLException sqlException) {
			throw new MyException("Tehnical problem occured. Refer log");
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new MyException("Files cannot be closed");

			}
		}

		return approvedLoanList;
	}

}
