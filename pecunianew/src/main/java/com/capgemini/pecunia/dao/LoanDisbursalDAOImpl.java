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
		System.out.println("hello");
		Connection connection = DBConnection.getInstance().getConnection();
		int loanRequests = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Loan> requestList = new ArrayList<Loan>();
		System.out.println("hello1");

		try {
			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_LOAN);
			System.out.println("hello2");
			resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);

			while (resultSet.next()) {
				System.out.println("hello4");
				
				int loanId=resultSet.getInt("loan_id");
				System.out.println(loanId);
				String account_id=resultSet.getString("account_id");
				System.out.println(account_id);
				Double amount = resultSet.getDouble("amount");
				System.out.println(amount);
				String type = resultSet.getString("type");
				System.out.println(type);
				int tenure = resultSet.getInt("tenure");
				System.out.println(tenure);
				int roi = resultSet.getInt("roi");
				System.out.println(roi);
				String status = resultSet.getString("loan_status");
				System.out.println(status);
				Double emi = resultSet.getDouble("emi");
				System.out.println(emi);
				int creditScore = resultSet.getInt("credit_score");
				System.out.println(creditScore);
				Loan loan = new Loan(loanId,account_id,amount,type,tenure,roi,status,emi,creditScore);
				requestList.add(loan);
				System.out.println("hello5");
				

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

			return  requestList;

	}


	public void releaseLoanSheet(List<Loan> loanList) throws IOException, MyException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		
		for (int i = 0; i < loanList.size(); i++) {
			try {
				double amountDue = amountToBePaid(loanList.get(i).getEmi(), loanList.get(i).getTenure());
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.INSERT_QUERY);
				System.out.println("hello88");
				preparedStatement.setInt(1, loanList.get(i).getLoanId());
				System.out.println("hello89");
				preparedStatement.setString(2, loanList.get(i).getAccountId());
				preparedStatement.setDouble(3, loanList.get(i).getAmount());
				System.out.println("hello90");
				preparedStatement.setDouble(4, amountDue);
				System.out.println("hello91");
				preparedStatement.setInt(5, loanList.get(i).getTenure());
				System.out.println("hello92");
			} catch (SQLException sqlException) {
				throw new MyException(sqlException.getMessage());
//				throw new MyException("Tehnical problem occured. Refer log");
			} 

		}
	}
	
	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException,MyException{
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		try {
			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_APPROVED_LOAN);
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
		}
		catch(SQLException sqlException) {
			throw new MyException("Tehnical problem occured. Refer log");
		}
		finally {
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


