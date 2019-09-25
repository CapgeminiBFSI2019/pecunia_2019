package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;

public class LoanDisbursalServiceImpl implements LoanDisbursalService {
	private ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>();

	public ArrayList<Loan> RetrieveAll() throws MyException, IOException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		return retrievedLoanRequests;
	}

	public void ApproveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException {
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (size > 0) {
			for (int i = 0; i < loanRequestList.size(); i++) {
				String loanStatus = loanRequestList.get(i).getLoanStatus();
				int creditScore = loanRequestList.get(i).getCreditScore();
				if (loanStatus != "Pending" && creditScore < 670) {
					rejectedLoanList.add(loanRequestList.get(i));
					loanRequestList.remove(i);
				}

			}

			loanDisbursedDAO.releaseLoanSheet(loanRequestList);
		}
	}
	
	
	
	public ArrayList<LoanDisbursal> ApprovedLoanList() throws IOException, MyException
	{
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
	    ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
	    approvedLoanList = loanDisbursedDAO.loanApprovedList();
	    return approvedLoanList;

	}
	
	public ArrayList<Loan> RejectedLoanRequests() throws MyException
	{
		return rejectedLoanList;
	}
	
	
   
}
