package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.service.LoanService;
import com.capgemini.pecunia.service.LoanServiceImpl;

import com.capgemini.pecunia.util.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



/**
 * Servlet implementation class LoanRequest1
 */
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		String AccountId = request.getParameter("Account Id");
		double amount = Double.parseDouble(request.getParameter("LoanAmount"));
		int tenure = Integer.parseInt(request.getParameter("Tenure"));
		double roi = Double.parseDouble(request.getParameter("roi"));
		int creditScore = Integer.parseInt(request.getParameter("CreditScore"));
		String loanStatus = request.getParameter("status");
		String loanType = request.getParameter("LoanType");
		Loan loan = new Loan();
		loan.setAccountId(AccountId);
		loan.setAmount(amount);
		loan.setTenure(tenure);
		loan.setCreditScore(creditScore);
		loan.setLoanStatus(loanStatus);
		loan.setRoi(roi);
		loan.setType(loanType);
		LoanService loanService = new LoanServiceImpl();
		double emi = LoanServiceImpl.calculateEMI(amount, tenure, roi);
		loan.setEmi(emi);

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result=false;
		PrintWriter out =response.getWriter();
		try {
			boolean isSuccess = loanService.createLoanRequest(loan);
			if (isSuccess) {
//				request.getRequestDispatcher("LoanRequest.html").include(request, response);
//				out.println("<script>$('#loan-request-success').toast('show');</script>");
				((ObjectNode) dataResponse).put("success", result);
				((ObjectNode) dataResponse).put("message",Constants.LOAN_REQUEST_SUCCESSFUL);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			boolean isSuccess = loanService.createLoanRequest(loan);
			if (isSuccess) {
				request.getRequestDispatcher("LoanRequest.html").include(request, response);
				out.println("<script>$('#loan-request-success').toast('show');</script>");


			} else {
				throw new LoanException("");
			}

		} catch (LoanException e) {

//			request.getRequestDispatcher("LoanRequest.html").include(request, response);
//			out.println("<script>$('#loan-request-failure').toast('show');</script>");
			((ObjectNode) dataResponse).put("success", result);
			((ObjectNode) dataResponse).put("message", e.getMessage());
		}
		finally {
			out.print(dataResponse);
		}
		out.close();
	    
	    		
	    
//		String AccountId = request.getParameter("Account Id");
//		double amount = Double.parseDouble(request.getParameter("LoanAmount"));
//		int tenure = Integer.parseInt(request.getParameter("Tenure"));
//		double roi = Double.parseDouble(request.getParameter("roi"));
//		int creditScore = Integer.parseInt(request.getParameter("CreditScore"));
//		String loanStatus = request.getParameter("status");
//		String loanType = request.getParameter("LoanType");
//		Loan loan = new Loan();
//		loan.setAccountId(AccountId);
//		loan.setAmount(amount);
//		loan.setTenure(tenure);
//		loan.setCreditScore(creditScore);
//		loan.setLoanStatus(loanStatus);
//		loan.setRoi(roi);
//		loan.setType(loanType);
//		LoanService loanService = new LoanServiceImpl();
//		double emi = LoanServiceImpl.calculateEMI(amount, tenure, roi);
//		loan.setEmi(emi);
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		try {
//			boolean isSuccess = loanService.createLoanRequest(loan);
//			if (isSuccess) {
//				request.getRequestDispatcher("LoanRequest.html").include(request, response);
//				out.println("<script>$('#loan-request-success').toast('show');</script>");
//
//			} else {
//				throw new LoanException("");
//			}
//
//		} catch (LoanException e) {
//			request.getRequestDispatcher("LoanRequest.html").include(request, response);
//			out.println("<script>$('#loan-request-failure').toast('show');</script>");
//		}
//		out.close();

			request.getRequestDispatcher("LoanRequest.html").include(request, response);
			out.println("<script>$('#loan-request-failure').toast('show');</script>");
		}
		out.close();

	}
}
