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
					"\r\n" + 
					"<head>\r\n" + 
					"    <!-- Required meta tags -->\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
					"\r\n" + 
					"    <!-- Bootstrap CSS -->\r\n" + 
					"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n" + 
					"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js \" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q \" crossorigin=\"anonymous \"></script>\r\n" + 
					"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js \" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl \" crossorigin=\"anonymous \"></script>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"    <script src=\"https://kit.fontawesome.com/a076d05399.js\"></script>\r\n" + 
					"    <title>Passbook Maintenance</title>\r\n" + 
					"\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"\r\n" + 
					"    <nav class=\"navbar navbar-expand-lg navbar-dark bg-info\">\r\n" + 
					"        <a class=\"navbar-brand\" href=\"MainPage.html\"><i class=\"fas fa-home text-light mr-2\" style=\"font-size: 24px;\"></i>Pecunia</a>\r\n" + 
					"        <a class=\"text-light d-block d-lg-none \">Rohan Patil</a>\r\n" + 
					"        <button class=\"navbar-toggler \" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
					"									<span class=\"navbar-toggler-icon\"></span>\r\n" + 
					"								</button>\r\n" + 
					"        <div id=\"navbarNavDropdown\" class=\"navbar-collapse collapse\">\r\n" + 
					"            <ul class=\"navbar-nav mr-auto\">\r\n" + 
					"                <li class=\"nav-item\">\r\n" + 
					"                    <a class=\"nav-link active\" href=\"passbookForm.html\">Passbook Form</a>\r\n" + 
					"                </li>\r\n" + 
					"\r\n" + 
					"            </ul>\r\n" + 
					"            <ul class=\"navbar-nav\">\r\n" + 
					"                <li class=\"nav-item d-none d-lg-block\" href=\"#\">\r\n" + 
					"                    <a class=\"nav-link disabled text-light\">Rohan Patil</a>\r\n" + 
					"                </li>\r\n" + 
					"                <li class=\"nav-item\">\r\n" + 
					"                    <a class=\"nav-link\" href=\"#\">Logout</a>\r\n" + 
					"                </li>\r\n" + 
					"            </ul>\r\n" + 
					"        </div>\r\n" + 
					"    </nav>\r\n" + 
					"    <div class=\"bg\"></div>" +
					"	<div class=\"container\">\r\n" + 
					"		<h2 class=\"col-8\">Passbook Details</h2>\r\n" + 
					"		<table class=\"table table-bordered\">\r\n" + 
					"			<thead class=\"thead-light\">\r\n");
			if(updatePassbook.size()>0)
			{	
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
				if(transaction.getOption().equalsIgnoreCase("cheque")) {
					out.write("<td>"+ transaction.getChequeId()+ "</td>");
				}
				else {
					out.write("<td>-</td>");
				}
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
			else
			{	out.write("No transaction to update. Passbook is up to date");
			
			}
			}
		catch (PecuniaException | PassbookException e) {
			
			request.getRequestDispatcher("passbookForm.html").include(request, response);
			out.println("<script>$('#passbook-toast').toast('show');</script>");
		}
	}
}
