package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;

public class LoanDisbursalServiceImpl implements LoanDisbursalService {
	private ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>();

	public ArrayList<Loan> retrieveAll() throws MyException, IOException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		return retrievedLoanRequests;
	}

	public void approveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException {
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if (size > 0) {
			for (int i = 0; i < loanRequestList.size(); i++) {
				String loanStatus = loanRequestList.get(i).getLoanStatus();
				int creditScore = loanRequestList.get(i).getCreditScore();
				if (creditScore < 670 || loanStatus != "Pending") {

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

	public ArrayList<Loan> rejectedLoanRequests() throws MyException {
	
		return rejectedLoanList;
	}
	
	public ArrayList<Loan> rejectedRequestsList(ArrayList<Loan> rejectedLoanList) throws IOException, MyException{
		for (int i = 0; i < rejectedLoanList.size(); i++) {
			if (rejectedLoanList.get(i).getLoanStatus() == "Pending") {
				rejectedLoanList.remove(i);
				Loan update = new Loan(rejectedLoanList.get(i).getLoanId(), rejectedLoanList.get(i).getAccountId(),
						rejectedLoanList.get(i).getAmount(), rejectedLoanList.get(i).getType(),
						rejectedLoanList.get(i).getTenure(), rejectedLoanList.get(i).getRoi(),
						"Rejected", rejectedLoanList.get(i).getEmi(),
						rejectedLoanList.get(i).getCreditScore());
				rejectedLoanList.add(i,update);
			}
			
	}
		return rejectedLoanList;

}
}
