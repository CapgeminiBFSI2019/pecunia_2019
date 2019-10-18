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
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class LoanDisbursalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3382684550563202404L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		JsonObject dataResponse = new JsonObject();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAll = new ArrayList<Loan>();
		String s = request.getParameter("show-loan-requests");
		if (s.equals("Retrieve all loan requests")) {
			try {
				retrieveAll = loanDisbursalService.retrieveAll();
				System.out.println("number of loan disbursal"+ retrieveAll.size());
				if(retrieveAll.size()>0)
				{
					for(Loan loanReqs : retrieveAll)
					{
//						System.out.println("Value : "+gson.toJson(transaction, Transaction.class));
						jsonArray.add(gson.toJson(loanReqs, Loan.class));
					}
					//System.out.println("jason array"+jsonArray);
					dataResponse.addProperty("success", true);
					dataResponse.add("data", jsonArray);
					}
				else
				{
					dataResponse.addProperty("success", true);
					dataResponse.addProperty("message", "No transaction to update");
				}
			} catch (PecuniaException | LoanDisbursalException e) {
					dataResponse.addProperty("success", false);
					dataResponse.addProperty("message", e.getMessage());
			} finally {
				out.print(dataResponse);
			}


		}
		
		if (s.equals("Show the loan requests to be accepted")) {
			try {
				retrieveAll = loanDisbursalService.approveLoan();
				System.out.println("number of loan disbursal"+ retrieveAll.size());
				if(retrieveAll.size()>0)
				{
					for(Loan loanReqs : retrieveAll)
					{
//						System.out.println("Value : "+gson.toJson(transaction, Transaction.class));
						jsonArray.add(gson.toJson(loanReqs, Loan.class));
					}
					//System.out.println("jason array"+jsonArray);
					dataResponse.addProperty("success", true);
					dataResponse.add("data", jsonArray);
					}
				else
				{
					dataResponse.addProperty("success", true);
					dataResponse.addProperty("message", "No loan requests are pending");
				}
			} catch (PecuniaException | LoanDisbursalException e) {
					dataResponse.addProperty("success", false);
					dataResponse.addProperty("message", e.getMessage());
			} finally {
				out.print(dataResponse);
			}
			
//			

		}
		
		if (s.equals("Show the loan requests to be rejected")) {
			
			try {
				retrieveAll = loanDisbursalService.rejectedLoanRequests();
				System.out.println("number of loan disbursal"+ retrieveAll.size());
				if(retrieveAll.size()>0)
				{
					for(Loan loanReqs : retrieveAll)
					{
//						System.out.println("Value : "+gson.toJson(transaction, Transaction.class));
						jsonArray.add(gson.toJson(loanReqs, Loan.class));
					}
					//System.out.println("jason array"+jsonArray);
					dataResponse.addProperty("success", true);
					dataResponse.add("data", jsonArray);
					}
				else
				{
					dataResponse.addProperty("success", true);
					dataResponse.addProperty("message", "No loan requests are pending");
				}
			} catch (PecuniaException | LoanDisbursalException e) {
					dataResponse.addProperty("success", false);
					dataResponse.addProperty("message", e.getMessage());
			} finally {
				out.print(dataResponse);
			}
			


		}
		
		
	}

}

