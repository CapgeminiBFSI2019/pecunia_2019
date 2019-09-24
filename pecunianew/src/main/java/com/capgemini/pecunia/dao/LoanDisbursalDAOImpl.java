package com.capgemini.pecunia.dao;

//loan_id	account_id	amount	type	tenure	roi	loan_status	emi	credit_score
//loan_id	account_id	disbursed_amount	due_amount	emi_to_be_paid

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class LoanDisbursalDAOImpl implements LoanDisbursalDAO {

	public double amountToBePaid(double emi, int tenure) {
		return emi * tenure;
	}

	public ArrayList<Loan> retrieveLoanList() throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();
		int loanRequests = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Loan> requestList = new ArrayList<Loan>();

		try {
			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Loan loan = new Loan();
				loan.setLoanId(resultSet.getInt(1));
				loan.setAccountId(resultSet.getString(2));
				loan.setAmount(resultSet.getDouble(3));
				loan.setType(resultSet.getString(4));
				loan.setTenure(resultSet.getInt(5));
				loan.setRoi(resultSet.getInt(6));
				loan.setLoanStatus(resultSet.getString(7));
				loan.setEmi(resultSet.getInt(8));
				loan.setCreditScore(resultSet.getInt(9));
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
		if (loanRequests == 0)
			return null;
		else
			return (ArrayList<Loan>) requestList;

	}

	@Override
	public ArrayList<Loan> approveLoan(ArrayList<Loan> loanList) {
		// TODO Auto-generated method stub
		return null;
	}

	public void releaseLoanSheet(ArrayList<Loan> loanList) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		
		for (int i = 0; i < loanList.size(); i++) {
			try {
				double amountDue = amountToBePaid(loanList.get(i).getEmi(), loanList.get(i).getTenure());
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.INSERT_QUERY);

				preparedStatement.setInt(2, loanList.get(i).getLoanId());
				preparedStatement.setString(3, loanList.get(i).getAccountId());
				preparedStatement.setDouble(4, loanList.get(i).getAmount());
				preparedStatement.setDouble(5, amountDue);
				preparedStatement.setDouble(6, loanList.get(i).getTenure());

			} catch (SQLException sqlException) {
				throw new MyException("Tehnical problem occured. Refer log");
			} finally {
				try {
					// resultSet.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException sqlException) {

					throw new MyException("Files cannot be closed");

				}
			}

		}
	}

}
