package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDisbursalService {
	public ArrayList<Loan> RetrieveAll() throws MyException;
	public void ApproveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException;
	

}
