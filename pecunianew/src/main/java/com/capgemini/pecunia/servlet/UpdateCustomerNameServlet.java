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
 * Servlet implementation class UpdateAccount
 */
public class UpdateCustomerNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accountId = request.getParameter("account-id");

		String custName = request.getParameter("name");

		response.setContentType("html/text");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		Customer customer = new Customer();
		account.setId(accountId);
		customer.setName(custName);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			boolean updated = ams.updateCustomerName(account, customer);
			if (updated) {
				request.getRequestDispatcher("updateName.html").include(request, response);
				out.println("<h1> Customer Name Successfully Updated </h1>");
			}
		} catch (PecuniaException | AccountException e) {
			out.println("<h1>Failure</h1><br>");
			request.getRequestDispatcher("updateName.html").include(request, response);

		}

	}

}
