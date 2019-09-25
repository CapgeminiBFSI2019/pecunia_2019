package com.capgemini.pecunia.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDisbursalDAO {
	public List<Loan> retrieveLoanList() throws IOException, MyException;

	public void releaseLoanSheet(List<Loan> loanList) throws IOException, MyException;

	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException, MyException;
	
	public void updateLoanAccount(ArrayList<LoanDisbursal>  loanApprovals) throws IOException, MyException;

}
