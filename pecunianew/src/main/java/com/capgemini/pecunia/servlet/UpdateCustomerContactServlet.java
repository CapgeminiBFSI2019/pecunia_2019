package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

/**
 * Servlet implementation class UpdateCustomerContactServlet
 */
public class UpdateCustomerContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accountId = request.getParameter("account-id");
		String custContact = request.getParameter("contact");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		Customer customer = new Customer();
		account.setId(accountId);
		customer.setContact(custContact);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			boolean updated = ams.updateCustomerContact(account, customer);
			if (updated) {
				out.println("<h1> Customer Contact Successfully Updated </h1>");

				request.getRequestDispatcher("updateContact.html").include(request, response);
			}
		} catch (PecuniaException | AccountException e) {
			out.println("<h1>Failure</h1><br>");
			request.getRequestDispatcher("updateContact.html").include(request, response);
		}
	}

}
