package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.MyException;

public class Demo {
	public static void main(String[] args) {

		LoanDisbursalDAO loanDisbursalDAO = new LoanDisbursalDAOImpl();
		List<Loan> l = new ArrayList<Loan>();
		try {
			l = loanDisbursalDAO.retrieveLoanList();
			loanDisbursalDAO.releaseLoanSheet(l);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}

	}

}
