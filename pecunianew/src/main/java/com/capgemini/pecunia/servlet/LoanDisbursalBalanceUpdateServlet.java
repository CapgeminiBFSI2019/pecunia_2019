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
				jsonArray.add(gson.toJson(msg, LoanDisbursal.class));
				
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
//		String res = request.getParameter("update-account-balance");
//		if (res.equals("Yes")) {
//			try {
//				
//				String msg = loanDisbursalService.updateExistingBalance(retrieveAccepted);
//
//				out.write("<!DOCTYPE html>\r\n" + 
//						"<html>\r\n" + 
//						"\r\n" + 
//						"<head>\r\n" + 
//						"    <!-- Required meta tags -->\r\n" + 
//						"    <meta charset=\"utf-8\">\r\n" + 
//						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
//						"\r\n" + 
//						"    <!-- Bootstrap CSS -->\r\n" + 
//						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
//						"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n" + 
//						"    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n" + 
//						"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js \" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q \" crossorigin=\"anonymous \"></script>\r\n" + 
//						"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js \" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl \" crossorigin=\"anonymous \"></script>\r\n" + 
//						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
//						"    <script src=\"https://kit.fontawesome.com/a076d05399.js\"></script>\r\n" + 
//						"    <title>Account Summary</title>\r\n" + 
//						"\r\n" + 
//						"</head>\r\n" + 
//						"\r\n" + 
//						"<body>\r\n" + 
//						"\r\n" + 
//						"    <nav class=\"navbar navbar-expand-lg navbar-dark bg-info\">\r\n" + 
//						"        <a class=\"navbar-brand\" href=\"MainPage.html\"><i class=\"fas fa-home text-light mr-2\" style=\"font-size: 24px;\"></i>Pecunia</a>\r\n" + 
//						"        <button class=\"navbar-toggler \" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
//						"									<span class=\"navbar-toggler-icon\"></span>\r\n" + 
//						"								</button>\r\n" + 
//						"        <div id=\"navbarNavDropdown\" class=\"navbar-collapse collapse\">\r\n" + 
//						"            <ul class=\"navbar-nav mr-auto\">\r\n" + 
//						"                <li class=\"nav-item\">\r\n" + 
//						"                    <a class=\"nav-link active\" href=\"loanDisbursal.html\">Loan Disbursal</a>\r\n" + 
//						"                </li>\r\n" + 
//						"\r\n" + 
//						"            </ul>\r\n" + 
//						"            <ul class=\"navbar-nav\">\r\n" + 
//						"                <li class=\"nav-item d-none d-lg-block\" href=\"#\">\r\n" + 
//						"                    <a class=\"nav-link disabled text-light\"></a>\r\n" + 
//						"                </li>\r\n" + 
//						"                <li class=\"nav-item\">\r\n" + 
//						"                    <a class=\"nav-link\" href=\"#\">Logout</a>\r\n" + 
//						"                </li>\r\n" + 
//						"            </ul>\r\n" + 
//						"        </div>\r\n" + 
//						"    </nav>\r\n" + 
//						"    <div class=\"bg\"></div>" +
//						"	<div class=\"container\">\r\n" + 
//						"		<h2>Account Balance Updation</h2>\r\n");
//				out.write("<h4>"+ msg+ "</h4>");
//				
//			} catch (PecuniaException | LoanDisbursalException | TransactionException e) {
//
//				
//				String msg = "Error occured";
//				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
//				out.println("<script>");
//                out.println("$('#failure-toast-body').html('" + msg + "');");
//                out.println("$('#loan-disbursal-failure').toast('show');");
//                out.println("</script>");
//			}
//		}
	}

}
