package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.Constants;
import com.capgemini.pecunia.util.LoggerMessage;

public class LoanDisbursalServiceImpl implements LoanDisbursalService {

	Logger logger = Logger.getRootLogger();
	

	public LoanDisbursalServiceImpl() {
	

	}

	TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
	ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
	

	/*******************************************************************************************************
	 * - Function Name : retrieveAll() - Input Parameters : None - Return Type :
	 * ArrayList<Loan> - Throws : PecuniaException, IOException,
	 * LoanDisbursalException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Retrieves loan requests
	 ********************************************************************************************************/

	public ArrayList<Loan> retrieveAll() throws PecuniaException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		if (retrievedLoanRequests.size() == 0) {
			logger.error(ErrorConstants.NO_LOAN_REQUESTS);
			throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);

		}
		logger.info(LoggerMessage.LOAN_REQUEST);
		return retrievedLoanRequests;
	}

	/*******************************************************************************************************
	 * - Function Name : approveLoan(ArrayList<Loan> loanRequestList) - Input
	 * Parameters : ArrayList<Loan> loanRequestList - Return Type : void - Throws :
	 * IOException, PecuniaException, LoanDisbursalException - Author : aninrana -
	 * Creation Date : 25/09/2019 - Description : Aprroving the loan request based
	 * on condition
	 ********************************************************************************************************/

	public ArrayList<Loan> approveLoan() throws IOException, PecuniaException, LoanDisbursalException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> acceptedLoanRequests = new ArrayList<Loan>();
		acceptedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveAcceptedLoanList();
		if (acceptedLoanRequests.size() == 0) {
			logger.error(ErrorConstants.NO_LOAN_REQUESTS);
			throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);

		}
		loanDisbursedDAO.releaseLoanSheet(acceptedLoanRequests);
		logger.info(LoggerMessage.LOAN_REQUEST);
		return acceptedLoanRequests;

	}

	/*******************************************************************************************************
	 * - Function Name : approvedLoanList() - Input Parameters : None - Return Type
	 * : ArrayList<LoanDisbursal> - Throws : PecuniaException,IOException - Author :
	 * aninrana - Creation Date : 25/09/2019 - Description : retrieving the loan
	 * disbursed data
	 * @throws LoanDisbursalException 
	 ********************************************************************************************************/

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, PecuniaException, LoanDisbursalException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();

		approvedLoanList = loanDisbursedDAO.loanApprovedList();
		if(approvedLoanList.size() == 0) {
			logger.error(ErrorConstants.NO_LOAN_REQUESTS);
			throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);
		}
		logger.info(LoggerMessage.APPROVED_LOAN_REQUESTS_FETCHED);
		return approvedLoanList;

	}

	/*******************************************************************************************************
	 * - Function Name : rejectedLoanRequests() - Input Parameters : void - Return
	 * Type : ArrayList<Loan> - Throws : PecuniaException, LoanDisbursalException -
	 * Author : aninrana - Creation Date : 25/09/2019 - Description : Retrieving the
	 * rejected loan rejected
	 * 
	 * @throws IOException
	 ********************************************************************************************************/

	public ArrayList<Loan> rejectedLoanRequests() throws PecuniaException, LoanDisbursalException, IOException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> rejectedLoanRequests = new ArrayList<Loan>();
		rejectedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveRejectedLoanList();
		if (rejectedLoanRequests.size() == 0) {
			logger.error(ErrorConstants.NO_LOAN_REQUESTS);
			throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);

		}
		logger.info(LoggerMessage.LOAN_REQUEST);
		return rejectedLoanRequests;
	}

	/*******************************************************************************************************
	 * - Function Name : updateLoanAccount(ArrayList<LoanDisbursal>
	 * updateLoanApprovals, int numberOfMonths) - Input Parameters :
	 * ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths - Return
	 * Type : void - Throws : throws PecuniaException - Author : aninrana - Creation
	 * Date : 25/09/2019 - Description : Updating the data in loan disbursed
	 * database
	 * 
	 * @throws LoanDisbursalException
	 ********************************************************************************************************/

	public String updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths)
			throws PecuniaException, LoanDisbursalException {
		String status = Constants.STATUS_CHECK[0];
		if (updateLoanApprovals != null) {
			LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
			for (int index = 0; index < updateLoanApprovals.size(); index++) {
				double updatedDueAmount = updateLoanApprovals.get(index).getDisbursedAmount()
						- (updateLoanApprovals.get(index).getDisbursedAmount()
								/ updateLoanApprovals.get(index).getNumberOfEmiToBePaid()) * numberOfMonths;

				double updatedTenure = updateLoanApprovals.get(index).getNumberOfEmiToBePaid() - numberOfMonths;

				String accountId = updateLoanApprovals.get(index).getAccountId();

				try {
					loanDisbursedDAO.updateLoanAccount(updateLoanApprovals, updatedDueAmount, updatedTenure, accountId);
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new LoanDisbursalException(e.getMessage());
				}

			}
		}

		else {
			status = Constants.STATUS_CHECK[1];
		}

		return status;
	}

	/*******************************************************************************************************
	 * - Function Name : updateLoanStatus(ArrayList<Loan> rejectedLoanList) - Input
	 * Parameters : ArrayList<Loan> rejectedLoanList - Return Type : void - Throws :
	 * PecuniaException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating the loan status after operation
	 * 
	 * @throws LoanDisbursalException
	 ********************************************************************************************************/

	public String updateLoanStatus(ArrayList<Loan> rejectedLoanList, ArrayList<Loan> approvedLoanList)
			throws PecuniaException, LoanDisbursalException {
		String status = Constants.STATUS_CHECK[0];
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (rejectedLoanList != null || approvedLoanList != null ) {
			try {
				for (int index = 0; index < rejectedLoanList.size(); index++) {
					int loanId = rejectedLoanList.get(index).getLoanId();
					loanDisbursedDAO.updateStatus(rejectedLoanList, loanId, Constants.LOAN_REQUEST_STATUS[2]);

				}

				for (int index = 0; index < approvedLoanList.size(); index++) {
					int loanId = approvedLoanList.get(index).getLoanId();
					loanDisbursedDAO.updateStatus(rejectedLoanList, loanId, Constants.LOAN_REQUEST_STATUS[1]);

				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new LoanDisbursalException(e.getMessage());
			}

		} else {
			status = Constants.STATUS_CHECK[1];
		}
		logger.info(LoggerMessage.UPDATE_LOAN_STATUS);
		return status;
	}

	/*******************************************************************************************************
	 * - Function Name : updateExistingBalance(ArrayList<Loan> approvedLoanRequests)
	 * - Input Parameters : ArrayList<Loan> approvedLoanRequests - Return Type :
	 * void - Throws : PecuniaException, TransactionException,
	 * LoanDisbursalException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating the Account balance of the customer
	 ********************************************************************************************************/

	public String updateExistingBalance(ArrayList<Loan> approvedLoanRequests)
			throws PecuniaException, TransactionException, LoanDisbursalException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		String status = Constants.STATUS_CHECK[0];
		for (int i = 0; i < approvedLoanRequests.size(); i++) {
			Account account = new Account();
			account.setId(approvedLoanRequests.get(i).getAccountId());
			double oldBalance = transactionDAOImpl.getBalance(account);
			double totalEMI = loanDisbursedDAO.totalEmi(approvedLoanRequests.get(i).getAccountId());
			double updatedBalance = oldBalance - totalEMI;
			if (updatedBalance < 0) {
				status = Constants.STATUS_CHECK[1];
			} else {
				account.setBalance(updatedBalance);
				transactionDAOImpl.updateBalance(account);
				updateLoanAccount(approvedLoanList, 1);
				status = Constants.STATUS_CHECK[0];
			}

		}
		logger.info(LoggerMessage.UPDATE_ACCOUNT_BALANCE);
		return status;
	}

	/*******************************************************************************************************
	 * - Function Name : updateExistingBalance(ArrayList<Loan> approvedLoanRequests)
	 * - Input Parameters : ArrayList<Loan> approvedLoanRequests - Return Type :
	 * void - Throws : PecuniaException, TransactionException,
	 * LoanDisbursalException - Author : aninrana - Creation Date : 25/09/2019 -
	 * Description : Updating the Account balance of the customer
	 ********************************************************************************************************/

	public String numberOfLoanAccounts(ArrayList<LoanDisbursal> approvedLoanRequests, String accountId) {
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < approvedLoanRequests.size(); index++) {
			String retrievedAccountId = approvedLoanRequests.get(index).getAccountId();
			
			if (retrievedAccountId.equals(accountId)) {

				sb.append(approvedLoanRequests.get(index).getLoanType() + "\n");
			

			}
		}

		return sb.toString();

	}

	

}
