package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.service.LoanService;
import com.capgemini.pecunia.service.LoanServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoanRequest1
 */
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {  }
		Map<String,String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();
		
		myMap = objectMapper.readValue(jb.toString(), HashMap.class);
	    String accountId = myMap.get("accountId");
	    double amount = Double.parseDouble(myMap.get("amount"));
	    int tenure=Integer.parseInt(myMap.get("tenure"));
	    int creditScore=Integer.parseInt(myMap.get("creditScore"));
	    String status=myMap.get("status");
	    String type=myMap.get("type");
	    double roi=Double.parseDouble(myMap.get("roi"));
	    Loan loan = new Loan();
	    loan.setAccountId(accountId);
		loan.setAmount(amount);
		loan.setTenure(tenure);
		loan.setCreditScore(creditScore);
		loan.setLoanStatus(status);
		loan.setRoi(roi);
		loan.setType(type);
		LoanService loanService = new LoanServiceImpl();
		double emi = LoanServiceImpl.calculateEMI(amount, tenure, roi);
		loan.setEmi(emi);
	    
	    		
	    
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
	}
}
