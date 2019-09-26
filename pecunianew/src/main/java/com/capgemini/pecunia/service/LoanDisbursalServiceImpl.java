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

	public ArrayList<Loan> retrieveAll() throws MyException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		if (retrievedLoanRequests.size() == 0) {
			throw new LoanDisbursalException("No loan request is present in database");
		}
		return retrievedLoanRequests;
	}

	public void approveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException, LoanDisbursalException {
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (loanRequestList.size() == 0) {
			throw new LoanDisbursalException("No loan request is present in database");
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

	}

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		approvedLoanList = loanDisbursedDAO.loanApprovedList();
		return approvedLoanList;

	}

	public ArrayList<Loan> rejectedLoanRequests() throws MyException, LoanDisbursalException {
		if (rejectedLoanList.size() == 0) {
			throw new LoanDisbursalException("No loan request has been rejected");
		}
		return rejectedLoanList;
	}

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

	public void updateLoanStatus(ArrayList<Loan> rejectedLoanList) throws MyException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();

		try {
			for (int i = 0; i < rejectedLoanList.size(); i++) {
				String accountId = rejectedLoanList.get(i).getAccountId();
				loanDisbursedDAO.updateStatus(rejectedLoanList, accountId, Constants.LOAN_REQUEST_STATUS[2]);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
