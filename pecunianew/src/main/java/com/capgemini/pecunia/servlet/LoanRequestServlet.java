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

import com.capgemini.pecunia.util.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Servlet implementation class LoanRequest1
 */
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
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
			HashMap<String,String> myMap = new HashMap<String, String>();

			ObjectMapper objectMapper = new ObjectMapper();
			
			myMap = objectMapper.readValue(jb.toString(), HashMap.class);
			 Loan loan = new Loan();
			
	    String accountId = myMap.get("accountId");
	    double amount = Double.parseDouble(myMap.get("amount"));
	    String type=myMap.get("type");
	    double roi=Double.parseDouble(myMap.get("roi"));
	    int creditScore=Integer.parseInt(myMap.get("creditScore"));
	    int tenure=Integer.parseInt(myMap.get("tenure"));
	    String status=myMap.get("status");
	    
	   
	    
	    loan.setAccountId(accountId);
	    loan.setAmount(amount);
	    loan.setType(type);
	    loan.setRoi(roi);
		loan.setCreditScore(creditScore);
		loan.setTenure(tenure);
		loan.setLoanStatus(status);	
		
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
				 ((ObjectNode) dataResponse).put("success", result);
					((ObjectNode) dataResponse).put("message",Constants.LOAN_REQUEST_SUCCESSFUL);


			} 
			else 
			{
				throw new LoanException("");
			}

		} 
		catch (LoanException e)
		{
			
		}
		}
	}

