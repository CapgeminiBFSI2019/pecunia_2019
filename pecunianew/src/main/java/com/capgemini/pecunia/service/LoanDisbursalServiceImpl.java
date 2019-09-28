package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

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

public class LoanDisbursalServiceImpl implements LoanDisbursalService {
	private ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>();

	TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
	ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();

	/*******************************************************************************************************
	 * - Function Name : retrieveAll() - Input Parameters : None - Return Type :
	 * ArrayList<Loan> - Throws : PecuniaException, IOException, LoanDisbursalException -
	 * Author : aninrana - Creation Date : 25/09/2019 - Description : Retrieves loan
	 * requests
	 ********************************************************************************************************/

	public ArrayList<Loan> retrieveAll() throws PecuniaException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		if (retrievedLoanRequests.size() == 0) {

			throw new LoanDisbursalException(Constants.NO_LOAN_REQUEST);

		}
		return retrievedLoanRequests;
	}

	/*******************************************************************************************************
	 * - Function Name : approveLoan(ArrayList<Loan> loanRequestList) - Input
	 * Parameters : ArrayList<Loan> loanRequestList - Return Type : void - Throws :
	 * IOException, PecuniaException, LoanDisbursalException - Author : aninrana -
	 * Creation Date : 25/09/2019 - Description : Aprroving the loan request based
	 * on condition
	 ********************************************************************************************************/

	public ArrayList<Loan> approveLoan(ArrayList<Loan> loanRequestList)
			throws IOException, PecuniaException, LoanDisbursalException {
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (loanRequestList.size() == 0) {
			throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);

		}
		if (size > 0) {
			for (int index = 0; index < loanRequestList.size(); index++) {

				int creditScore = loanRequestList.get(index).getCreditScore();
				if (creditScore < 670) {

					rejectedLoanList.add(loanRequestList.get(index));
					loanRequestList.remove(index);
				}

			}

			loanDisbursedDAO.releaseLoanSheet(loanRequestList);
		}

		return loanRequestList;

	}

	/*******************************************************************************************************
	 * - Function Name : approvedLoanList() - Input Parameters : None - Return Type
	 * : ArrayList<LoanDisbursal> - Throws : PecuniaException,IOException - Author :
	 * aninrana - Creation Date : 25/09/2019 - Description : retrieving the loan
	 * disbursed data
	 ********************************************************************************************************/

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, PecuniaException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();

		approvedLoanList = loanDisbursedDAO.loanApprovedList();
		return approvedLoanList;

	}

	/*******************************************************************************************************
	 * - Function Name : rejectedLoanRequests() - Input Parameters : void - Return
	 * Type : ArrayList<Loan> - Throws : PecuniaException, LoanDisbursalException -
	 * Author : aninrana - Creation Date : 25/09/2019 - Description : Retrieving the
	 * rejected loan rejected
	 ********************************************************************************************************/

	public ArrayList<Loan> rejectedLoanRequests() throws PecuniaException, LoanDisbursalException {
		if (rejectedLoanList.size() == 0) {
			throw new LoanDisbursalException("No loan request has been rejected");
		}
		return rejectedLoanList;
	}

	/*******************************************************************************************************
	 * - Function Name : updateLoanAccount(ArrayList<LoanDisbursal>
	 * updateLoanApprovals, int numberOfMonths) - Input Parameters :
	 * ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths - Return
	 * Type : void - Throws : throws PecuniaException - Author : aninrana - Creation Date
	 * : 25/09/2019 - Description : Updating the data in loan disbursed database
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
				} catch (IOException e) {

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
	 * PecuniaException - Author : aninrana - Creation Date : 25/09/2019 - Description :
	 * Updating the loan status after operation
	 * 
	 * @throws LoanDisbursalException
	 ********************************************************************************************************/

	public String updateLoanStatus(ArrayList<Loan> rejectedLoanList, ArrayList<Loan> approvedLoanList)
			throws PecuniaException, LoanDisbursalException {
		String status = Constants.STATUS_CHECK[0];
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (rejectedLoanList != null || approvedLoanList != null) {
			try {
				for (int index = 0; index < rejectedLoanList.size(); index++) {
					String accountId = rejectedLoanList.get(index).getAccountId();
					loanDisbursedDAO.updateStatus(rejectedLoanList, accountId, Constants.LOAN_REQUEST_STATUS[2]);

				}

				for (int index = 0; index < approvedLoanList.size(); index++) {
					String accountId = approvedLoanList.get(index).getAccountId();
					loanDisbursedDAO.updateStatus(rejectedLoanList, accountId, Constants.LOAN_REQUEST_STATUS[1]);

				}

			} catch (IOException e) {

				throw new LoanDisbursalException(e.getMessage());
			}

		} else {
			status = Constants.STATUS_CHECK[1];
		}
		return status;
	}

	/*******************************************************************************************************
	 * - Function Name : updateExistingBalance(ArrayList<Loan> approvedLoanRequests)
	 * - Input Parameters : ArrayList<Loan> approvedLoanRequests - Return Type :
	 * void - Throws : PecuniaException, TransactionException, LoanDisbursalException -
	 * Author : aninrana - Creation Date : 25/09/2019 - Description : Updating the
	 * Account balance of the customer
	 ********************************************************************************************************/

	public String updateExistingBalance(ArrayList<Loan> approvedLoanRequests)
			throws PecuniaException, TransactionException, LoanDisbursalException {
		String status = Constants.STATUS_CHECK[0];
		for (int index = 0; index < approvedLoanRequests.size(); index++) {
			Account account = new Account();
			account.setId(approvedLoanRequests.get(index).getAccountId());
			double oldBalance = transactionDAOImpl.getBalance(account);
			double updatedBalance = oldBalance - approvedLoanRequests.get(index).getEmi();
			if (updatedBalance < 0) {
				System.out.println("Not enough balance");
				status = Constants.STATUS_CHECK[1];
			} else {
				account.setBalance(updatedBalance);
				transactionDAOImpl.updateBalance(account);
				updateLoanAccount(approvedLoanList, 1);
				status = Constants.STATUS_CHECK[0];
			}

		}
		return status;
	}

}
