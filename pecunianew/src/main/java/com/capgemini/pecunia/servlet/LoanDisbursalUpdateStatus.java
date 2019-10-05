package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;

public class LoanDisbursalUpdateStatus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2020330281836221540L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAccepted = new ArrayList<Loan>();
		ArrayList<Loan> retrieveRejected = new ArrayList<Loan>();
		try {
			retrieveAccepted = loanDisbursalService.approveLoan();
		} catch (PecuniaException | LoanDisbursalException e) {
		
			retrieveAccepted = null;
		}
		try {
			retrieveRejected = loanDisbursalService.rejectedLoanRequests();
		} catch (PecuniaException | LoanDisbursalException e) {
			
			retrieveRejected = null;
		}
	}
}
