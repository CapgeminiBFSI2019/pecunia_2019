package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LoanDisbursalBalanceUpdateServlet extends HttpServlet {


	private static final long serialVersionUID = 2212946981490266552L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAccepted = new ArrayList<Loan>();
		try {
			retrieveAccepted = loanDisbursalService.approveLoanWithoutStatus();
		} catch (PecuniaException | LoanDisbursalException e1) {
			
			e1.printStackTrace();
		}
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		
		
        JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		JsonObject dataResponse = new JsonObject();
		
		
		try {
			String msg = loanDisbursalService.updateExistingBalance(retrieveAccepted);
			
			if(msg != null)
			{
				
//					System.out.println("Value : "+gson.toJson(transaction, Transaction.class));
//				jsonArray.add(gson.toJson(msg, LoanDisbursal.class));
				
				//System.out.println("jason array"+jsonArray);
				dataResponse.addProperty("success", true);
				dataResponse.addProperty("message", msg);
				} 
			
		} catch (PecuniaException | LoanDisbursalException | TransactionException e) {
				dataResponse.addProperty("success", false);
				dataResponse.addProperty("message", e.getMessage());
		} finally {
			out.print(dataResponse);
		}

	}

}
