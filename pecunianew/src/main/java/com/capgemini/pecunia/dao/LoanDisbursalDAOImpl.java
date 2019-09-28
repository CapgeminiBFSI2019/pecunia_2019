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
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.DBConnection;

public class LoanDisbursalDAOImpl implements LoanDisbursalDAO {

	private int loanId;
	private String accountId;
	private Double amount;
	private String type;
	private int tenure;
	private int roi;
	private String status;
	private Double emi;
	private int creditScore;
	private int loanDisbursedId;
	private int loanId1;
	private String accountId1;
	private Double disbursedAmount;
	private double dueAmount;
	private double emiToBePaid;

	/*******************************************************************************************************
	 * - Function Name : amountToBePaid(double emi, int tenure) - Input Parameters
	 * :double emi, int tenure - Return Type : double - Throws : None - Author :
	 * aninrana - Creation Date : 25/09/2019 - Description : Calculating the total
	 * amount to be paid after interest.
	 ********************************************************************************************************/

	public double amountToBePaid(double emi, int tenure) {
		return emi * tenure;
	}

	/*******************************************************************************************************
	 * - Function Name : retrieveLoanList() - Input Parameters :none - Return Type :
	 * List<Loan> - Throws : None - Author : IOException, PecuniaException -
	 * Creation Date : 25/09/2019 - Description : Retrieving the loan requests from
	 * the database
	 ********************************************************************************************************/

	public List<Loan> retrieveLoanList() throws IOException, PecuniaException {

		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Loan> requestList = new ArrayList<Loan>();

		try {
			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_LOAN);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				loanId = resultSet.getInt("loan_id");
				accountId = resultSet.getString("account_id");
				amount = resultSet.getDouble("amount");
				type = resultSet.getString("type");
				tenure = resultSet.getInt("tenure");
				roi = resultSet.getInt("roi");
				status = resultSet.getString("loan_status");
				emi = resultSet.getDouble("emi");
				creditScore = resultSet.getInt("credit_score");
				Loan loan = new Loan(loanId, accountId, amount, type, tenure, roi, status, emi, creditScore);
				requestList.add(loan);

			}
		}

		catch (SQLException sqlException) {
			throw new PecuniaException(ErrorConstants.CONNECTION_FAILURE);
		}

		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new PecuniaException(ErrorConstants.FILE_CLOSING_FAILURE);

			}
		}

		return requestList;

	}

	/*******************************************************************************************************
	 * - Function Name : releaseLoanSheet(List<Loan> loanList) - Input Parameters
	 * :List<Loan> loanList - Return Type : void - Throws : IOException,
	 * PecuniaException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating the data in loan disbursed database
	 ********************************************************************************************************/

	public void releaseLoanSheet(List<Loan> loanList) throws IOException, PecuniaException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		try {
			for (int index = 0; index < loanList.size(); index++) {
				double amountDue = amountToBePaid(loanList.get(index).getEmi(), loanList.get(index).getTenure());
				preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.INSERT_QUERY);
				preparedStatement.setInt(1, loanList.get(index).getLoanId());
				preparedStatement.setString(2, loanList.get(index).getAccountId());
				preparedStatement.setDouble(3, loanList.get(index).getAmount());
				preparedStatement.setDouble(4, amountDue);
				preparedStatement.setInt(5, loanList.get(index).getTenure());
				preparedStatement.execute();
			}

		} catch (SQLException sqlException) {
			throw new PecuniaException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new PecuniaException(ErrorConstants.FILE_CLOSING_FAILURE);

			}
		}

	}

	/*******************************************************************************************************
	 * - Function Name : loanApprovedList() - Input Parameters : None - Return Type
	 * : ArrayList<LoanDisbursal> - Throws : IOException, PecuniaException - Author
	 * : aninrana - Creation Date : 25/09/2019 - Description : returning the list of
	 * loan customers whose loan request has been approved
	 ********************************************************************************************************/

	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException, PecuniaException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		try {
			preparedStatement = connection
					.prepareStatement(LoanDisbursalQuerryMapper.RETRIVE_ALL_QUERY_FROM_APPROVED_LOAN);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				loanDisbursedId = resultSet.getInt("loan_disbursal_id");
				loanId1 = resultSet.getInt("loan_id");
				accountId = resultSet.getString("account_id");
				disbursedAmount = resultSet.getDouble("disbursed_amount");
				dueAmount = resultSet.getDouble("due_amount");
				emiToBePaid = resultSet.getDouble("emi_to_be_paid");
				LoanDisbursal getDetails = new LoanDisbursal(loanDisbursedId, loanId1, accountId1, disbursedAmount,
						dueAmount, emiToBePaid);
				approvedLoanList.add(getDetails);

			}
		} catch (SQLException sqlException) {
			throw new PecuniaException(ErrorConstants.CONNECTION_FAILURE);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new PecuniaException(ErrorConstants.FILE_CLOSING_FAILURE);

			}
		}

		return approvedLoanList;
	}

	/*******************************************************************************************************
	 * - Function Name : updateLoanAccount(ArrayList<LoanDisbursal> loanApprovals,
	 * double dueAmount,double tenure, String accountId) - Input Parameters :
	 * ArrayList<LoanDisbursal> loanApprovals, double dueAmount,double tenure,
	 * String accountId - Return Type : void - Throws : IOException,
	 * PecuniaException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating the main account balance of the loan customer
	 ********************************************************************************************************/

	public void updateLoanAccount(ArrayList<LoanDisbursal> loanApprovals, double dueAmount, double tenure,
			String accountId) throws IOException, PecuniaException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.UPDATE_LOAN_ACCOUNT);
			preparedStatement.setDouble(1, dueAmount);
			preparedStatement.setDouble(2, tenure);
			preparedStatement.setString(3, accountId);
			preparedStatement.execute();

		} catch (SQLException sqlException) {
			throw new PecuniaException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new PecuniaException(ErrorConstants.FILE_CLOSING_FAILURE);

			}
		}

	}

	/*******************************************************************************************************
	 * - Function Name : updateStatus(ArrayList<Loan> loanRequests, String
	 * accountId, String Status) - Input Parameters : ArrayList<Loan> loanRequests,
	 * String accountId, String Status - Return Type : void - Throws : IOException,
	 * PecuniaException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating loan status of the loan customers
	 ********************************************************************************************************/

	public void updateStatus(ArrayList<Loan> loanRequests, String accountId, String Status)
			throws IOException, PecuniaException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(LoanDisbursalQuerryMapper.UPDATE_LOAN_STATUS);
			preparedStatement.setString(1, Status);
			preparedStatement.setString(2, accountId);
			preparedStatement.execute();

		} catch (SQLException sqlException) {
			throw new PecuniaException(sqlException.getMessage());

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {

				throw new PecuniaException(ErrorConstants.FILE_CLOSING_FAILURE);

			}
		}
	}

}