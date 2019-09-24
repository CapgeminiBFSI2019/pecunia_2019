package com.capgemini.pecunia.dao;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDisbursalDAO {
	public ArrayList<Loan> retrieveLoanList() throws IOException,MyException;


	public void releaseLoanSheet(ArrayList<Loan> loanList) throws IOException,MyException;

	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException,MyException;



}

