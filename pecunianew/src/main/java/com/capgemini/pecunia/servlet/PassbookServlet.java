package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;

public class PassbookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String accountId = request.getParameter("accountID");
		Account obj= new Account();
		obj.setId(accountId);
		List<Transaction> updatePassbook;
		
		PassbookMaintenanceService passbookService = new PassbookMaintenanceServiceImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
		try {
			updatePassbook= passbookService.updatePassbook(accountId);
			out.write("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<title>Passbook Details</title>\r\n" + 
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
					"		<h2 class=\"col-8\">Passbook Details</h2>\r\n" + 
					"		<table class=\"table table-bordered\">\r\n" + 
					"			<thead class=\"thead-light\">\r\n");
			
			out.write("<th colspan=9>Account ID:" + accountId +"</th>\r\n" + 
					"				</tr>\r\n" + 
					"				<tr>\r\n" + 
					"					<th>Id</th>\r\n" + 
					"					<th>Date</th>\r\n" + 
					"					<th>Amount</th>\r\n" + 
					"					<th>From</th>\r\n" + 
					"					<th>To</th>\r\n" + 
					"					<th>Type</th>\r\n" + 
					"					<th>Option</th>\r\n" + 
					"					<th>Cheque ID</th>\r\n" + 
					"					<th>Closing Balance</th>\r\n" + 
					"				</tr>\r\n" + 
					"			</thead>");
			out.write("<tbody>"); 
			for(Transaction transaction: updatePassbook) 
			{
				out.write("<tr>");
				out.write("<td>"+ transaction.getId()+ "</td>");
				out.write("<td>"+ transaction.getTransDate()+ "</td>");
				out.write("<td>"+ transaction.getAmount()+ "</td>");
				out.write("<td>"+ transaction.getTransFrom()+ "</td>");
				out.write("<td>"+ transaction.getTransTo()+ "</td>");
				out.write("<td>"+ transaction.getType()+ "</td>");
				out.write("<td>"+ transaction.getOption()+ "</td>");
				out.write("<td>"+ transaction.getChequeId()+ "</td>");
				out.write("<td>"+ transaction.getClosingBalance()+ "</td>");
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
		catch (PecuniaException | PassbookException e) {
			out.println("<br><h4 class='text-danger'>Invalid account ID.</h4><br>");
		}
	}
}