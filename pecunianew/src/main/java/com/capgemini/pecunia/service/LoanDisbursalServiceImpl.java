package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Constants;

public class LoanDisbursalServiceImpl implements LoanDisbursalService {
	private ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>();
	
	/*******************************************************************************************************
	 * - Function Name : retrieveAll()
	 * - Input Parameters : None
	 * - Return Type : ArrayList<Loan> 
	 * - Throws : MyException, IOException, LoanDisbursalException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : Retrieves loan requests
	 ********************************************************************************************************/
	
	public ArrayList<Loan> retrieveAll() throws MyException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		if (retrievedLoanRequests.size() == 0) {

			throw new LoanDisbursalException(Constants.NO_LOAN_REQUESTS);

	

		}
		return retrievedLoanRequests;
	}
	
	/*******************************************************************************************************
	 * - Function Name : approveLoan(ArrayList<Loan> loanRequestList)
	 * - Input Parameters : ArrayList<Loan> loanRequestList
	 * - Return Type : void 
	 * - Throws : IOException, MyException, LoanDisbursalException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : Aprroving the loan request based on condition
	 ********************************************************************************************************/


	

	public ArrayList<Loan> approveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException, LoanDisbursalException {
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (loanRequestList.size() == 0) {
			throw new LoanDisbursalException(Constants.NO_REJECTED_LOAN_REQUESTS);

		}
		if (size > 0) {
			for (int i = 0; i < loanRequestList.size(); i++) {

				int creditScore = loanRequestList.get(i).getCreditScore();
				if (creditScore < 670) {

					rejectedLoanList.add(loanRequestList.get(i));
					loanRequestList.remove(i);
				}

			}

			loanDisbursedDAO.releaseLoanSheet(loanRequestList);
		}

		
		return loanRequestList;



	}
	
	/*******************************************************************************************************
	 * - Function Name : approvedLoanList()
	 * - Input Parameters : None
	 * - Return Type : ArrayList<LoanDisbursal> 
	 * - Throws : MyException,IOException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : retrieving the loan disbursed data 
	 ********************************************************************************************************/

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		approvedLoanList = loanDisbursedDAO.loanApprovedList();
		return approvedLoanList;

	}
	
	/*******************************************************************************************************
	 * - Function Name : rejectedLoanRequests()
	 * - Input Parameters : void
	 * - Return Type : ArrayList<Loan> 
	 * - Throws : MyException, LoanDisbursalException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : Retrieving the rejected loan rejected
	 ********************************************************************************************************/

	public ArrayList<Loan> rejectedLoanRequests() throws MyException, LoanDisbursalException {
		if (rejectedLoanList.size() == 0) {

			throw new LoanDisbursalException(Constants.NO_REJECTED_LOAN_REQUESTS);
		}
		return rejectedLoanList;
	}
	
	/*******************************************************************************************************
	 * - Function Name : updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths)
	 * - Input Parameters : ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths
	 * - Return Type : void 
	 * - Throws : throws MyException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : Updating the data in loan disbursed database
	 ********************************************************************************************************/

		


	public void updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths) throws MyException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		for (int i = 0; i < updateLoanApprovals.size(); i++) {
			double updatedDueAmount = updateLoanApprovals.get(i).getDisbursedAmount()
					- (updateLoanApprovals.get(i).getDisbursedAmount()
							/ updateLoanApprovals.get(i).getNumberOfEmiToBePaid()) * numberOfMonths;

			double updatedTenure = updateLoanApprovals.get(i).getNumberOfEmiToBePaid() - numberOfMonths;

			String accountId = updateLoanApprovals.get(i).getAccountId();

			try {
				loanDisbursedDAO.updateLoanAccount(updateLoanApprovals, updatedDueAmount, updatedTenure, accountId);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	
	/*******************************************************************************************************
	 * - Function Name : updateLoanStatus(ArrayList<Loan> rejectedLoanList)
	 * - Input Parameters : ArrayList<Loan> rejectedLoanList
	 * - Return Type : void 
	 * - Throws : MyException 
	 * - Author : aninrana
	 * - Creation Date : 25/09/2019 
	 * - Description : Updating the loan status after operation
	 ********************************************************************************************************/

	public void updateLoanStatus(ArrayList<Loan> rejectedLoanList, ArrayList<Loan> approvedLoanList) throws MyException {

	


		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();

		try {
			for (int i = 0; i < rejectedLoanList.size(); i++) {
				String accountId = rejectedLoanList.get(i).getAccountId();
				loanDisbursedDAO.updateStatus(rejectedLoanList, accountId, Constants.LOAN_REQUEST_STATUS[2]);

			
				
				
			
			}
			
			for (int i = 0; i < approvedLoanList.size(); i++) {
				String accountId = approvedLoanList.get(i).getAccountId();
				loanDisbursedDAO.updateStatus(rejectedLoanList, accountId, Constants.LOAN_REQUEST_STATUS[1]);
			
				
				
			


			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	


}
