package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;

public class LoanDisbursalDatabase extends HttpServlet {
	

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<LoanDisbursal> retrieveLoanDisbursedData = new ArrayList<LoanDisbursal>();
		String res = request.getParameter("show-loan-disbursal");
		if(res.equals("Yes")) {
			try {
				retrieveLoanDisbursedData = loanDisbursalService.approvedLoanList();
				
				out.write("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<title>Loan Disbursed Details</title>\r\n" + 
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
						"		<h2 class=\"col-8\">Loan Disbursed Details</h2>\r\n" + 
						"		<table class=\"table table-bordered\">\r\n" + 
						"			<thead class=\"thead-light\">\r\n");
				
				out.write(
						"					<th>Loan Disbursal Id</th>\r\n" + 
						"					<th>Loan Id</th>\r\n" + 
						"					<th>Disbursed Amount</th>\r\n" + 
						"					<th>Due Amount</th>\r\n" + 
						"					<th>Number Of Emi To Be Paid</th>\r\n" + 
						"					<th>Loan Type</th>\r\n" + 
						"				</tr>\r\n" + 
						"			</thead>");
				out.write("<tbody>"); 


				for(LoanDisbursal loanDisbursed: retrieveLoanDisbursedData) 
				{
					out.write("<tr>");
					out.write("<td>"+ loanDisbursed.getLoanDisbursalId()+ "</td>");
					out.write("<td>"+ loanDisbursed.getLoanId()+ "</td>");
					out.write("<td>"+ loanDisbursed.getDisbursedAmount()+ "</td>");
					out.write("<td>"+ loanDisbursed.getDueAmount()+ "</td>");
					out.write("<td>"+ loanDisbursed.getNumberOfEmiToBePaid()+ "</td>");
					out.write("<td>"+ loanDisbursed.getLoanType()+ "</td>");
					out.write("</tr>");
				}
				out.write("	</tr>\r\n" + 
						"			</tbody>\r\n" + 
						"		</table>\r\n" + 
						"	</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"</html>"); 
				
			} catch (PecuniaException | LoanDisbursalException e) {
		
				 request.getRequestDispatcher("loanDisbursal.html").include(request,response);
					out.println("<h3>Either no loan requests has been approved or connection error</h3>");
			}
		}
		

		
	}
	
	

}

