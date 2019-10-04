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

public class LoanDisbursalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3382684550563202404L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAll = new ArrayList<Loan>();
		ArrayList<Loan> retrieveApproved = new ArrayList<Loan>();
		String s = request.getParameter("show-loan-requests");
		if (s.equals("retrieve all")) {
			try {
				retrieveAll = loanDisbursalService.retrieveAll();

				out.write("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<title>Loan Request Details</title>\r\n" + 
						"<meta charset=\"utf-8\">\r\n" + 
						"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
						"<link rel=\"stylesheet\"\r\n" + 
						"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n" + 
						"<script\r\n" + 
						"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<div class=\"jumbotron bg-info text-white\">\r\n" + 
						"			<h1 align=\"center\" class=\"bg-info text-white\">\r\n" + 
						"				<b><i>Pecunia</i></b>\r\n" + 
						"			</h1>\r\n" + 
						"		</div>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<h2 class=\"col-8\">Loan Request Details</h2>\r\n" + 
						"		<table class=\"table table-bordered\">\r\n" + 
						"			<thead class=\"thead-light\">\r\n");
				
				out.write(
						"					<th>Loan Id</th>\r\n" + 
						"					<th>Account Id</th>\r\n" + 
						"					<th>Amount</th>\r\n" + 
						"					<th>Type</th>\r\n" + 
						"					<th>Tenure</th>\r\n" + 
						"					<th>ROI</th>\r\n" + 
						"					<th>Loan Status</th>\r\n" + 
						"					<th>EMI</th>\r\n" + 
						"                   <th>Credit Score</th>\r\n" + 
						"				</tr>\r\n" + 
						"			</thead>");
				out.write("<tbody>"); 

				
				for(Loan loanRequests: retrieveAll) 
				{
					out.write("<tr>");
					out.write("<td>"+ loanRequests.getLoanId()+ "</td>");
					out.write("<td>"+ loanRequests.getAccountId()+ "</td>");
					out.write("<td>"+ loanRequests.getAmount()+ "</td>");
					out.write("<td>"+ loanRequests.getType()+ "</td>");
					out.write("<td>"+ loanRequests.getTenure()+ "</td>");
					out.write("<td>"+ loanRequests.getRoi()+ "</td>");
					out.write("<td>"+ loanRequests.getLoanStatus()+ "</td>");
					out.write("<td>"+ loanRequests.getEmi()+ "</td>");
					out.write("<td>"+ loanRequests.getCreditScore()+ "</td>");
					out.write("</tr>");
				}
				out.write("	</tr>\r\n" + 
						"			</tbody>\r\n" + 
						"		</table>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"</html>"); 
				

				}
				
			 catch (PecuniaException | LoanDisbursalException e) {
				 request.getRequestDispatcher("loanDisbursal.html").include(request,response);
				out.println("<h3>Failure retrieving error</h3>");

			}

		}
		
		if (s.equals("retrieve approved")) {
			try {
				retrieveAll = loanDisbursalService.approveLoan();

				out.write("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<title> Approved Loan Request Details </title>\r\n" + 
						"<meta charset=\"utf-8\">\r\n" + 
						"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
						"<link rel=\"stylesheet\"\r\n" + 
						"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n" + 
						"<script\r\n" + 
						"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<div class=\"jumbotron bg-info text-white\">\r\n" + 
						"			<h1 align=\"center\" class=\"bg-info text-white\">\r\n" + 
						"				<b><i>Pecunia</i></b>\r\n" + 
						"			</h1>\r\n" + 
						"		</div>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<h2 class=\"col-8\">Approved Loan Request Details</h2>\r\n" + 
						"		<table class=\"table table-bordered\">\r\n" + 
						"			<thead class=\"thead-light\">\r\n");
				
				out.write(
						"					<th>Loan Id</th>\r\n" + 
						"					<th>Account Id</th>\r\n" + 
						"					<th>Amount</th>\r\n" + 
						"					<th>Type</th>\r\n" + 
						"					<th>Tenure</th>\r\n" + 
						"					<th>ROI</th>\r\n" +  
						"					<th>EMI</th>\r\n" + 
						"                   <th>Credit Score</th>\r\n" + 
						"				</tr>\r\n" + 
						"			</thead>");
				out.write("<tbody>"); 

				// request.getRequestDispatcher("loanDisbursal.html").include(request,
				for(Loan loanRequests: retrieveAll) 
				{
					out.write("<tr>");
					out.write("<td>"+ loanRequests.getLoanId()+ "</td>");
					out.write("<td>"+ loanRequests.getAccountId()+ "</td>");
					out.write("<td>"+ loanRequests.getAmount()+ "</td>");
					out.write("<td>"+ loanRequests.getType()+ "</td>");
					out.write("<td>"+ loanRequests.getTenure()+ "</td>");
					out.write("<td>"+ loanRequests.getRoi()+ "</td>");
					out.write("<td>"+ loanRequests.getEmi()+ "</td>");
					out.write("<td>"+ loanRequests.getCreditScore()+ "</td>");
					out.write("</tr>");
				}
				out.write("	</tr>\r\n" + 
						"			</tbody>\r\n" + 
						"		</table>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"</html>"); 
				

				}
				
			 catch (PecuniaException | LoanDisbursalException e) {
				 request.getRequestDispatcher("loanDisbursal.html").include(request,response);
				 out.println("<h3>Failure retrieving error as no loan request is pending</h3>");

			}

		}
		
		if (s.equals("retrieve rejected")) {
			try {
				retrieveAll = loanDisbursalService.rejectedLoanRequests();

				out.write("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<title> Rejected Loan Request Details </title>\r\n" + 
						"<meta charset=\"utf-8\">\r\n" + 
						"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
						"<link rel=\"stylesheet\"\r\n" + 
						"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n" + 
						"<script\r\n" + 
						"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n" + 
						"<script\r\n" + 
						"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<div class=\"jumbotron bg-info text-white\">\r\n" + 
						"			<h1 align=\"center\" class=\"bg-info text-white\">\r\n" + 
						"				<b><i>Pecunia</i></b>\r\n" + 
						"			</h1>\r\n" + 
						"		</div>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"	<div class=\"container\">\r\n" + 
						"		<h2 class=\"col-8\">Rejected Loan Request Details</h2>\r\n" + 
						"		<table class=\"table table-bordered\">\r\n" + 
						"			<thead class=\"thead-light\">\r\n");
				
				out.write(
						"					<th>Loan Id</th>\r\n" + 
						"					<th>Account Id</th>\r\n" + 
						"					<th>Amount</th>\r\n" + 
						"					<th>Type</th>\r\n" + 
						"					<th>Tenure</th>\r\n" + 
						"					<th>ROI</th>\r\n" +  
						"					<th>EMI</th>\r\n" + 
						"                   <th>Credit Score</th>\r\n" + 
						"				</tr>\r\n" + 
						"			</thead>");
				out.write("<tbody>"); 

				// request.getRequestDispatcher("loanDisbursal.html").include(request,
				for(Loan loanRequests: retrieveAll) 
				{
					out.write("<tr>");
					out.write("<td>"+ loanRequests.getLoanId()+ "</td>");
					out.write("<td>"+ loanRequests.getAccountId()+ "</td>");
					out.write("<td>"+ loanRequests.getAmount()+ "</td>");
					out.write("<td>"+ loanRequests.getType()+ "</td>");
					out.write("<td>"+ loanRequests.getTenure()+ "</td>");
					out.write("<td>"+ loanRequests.getRoi()+ "</td>");
					out.write("<td>"+ loanRequests.getEmi()+ "</td>");
					out.write("<td>"+ loanRequests.getCreditScore()+ "</td>");
					out.write("</tr>");
				}
				out.write("	</tr>\r\n" + 
						"			</tbody>\r\n" + 
						"		</table>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"</html>"); 
				

				}
				
			 catch (PecuniaException | LoanDisbursalException e) {
				 request.getRequestDispatcher("loanDisbursal.html").include(request,response);
				out.println("<h3>Failure retrieving error as no loan request is pending</h3>");

			}

		}
		
		
	}

}

