package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accountId = request.getParameter("account-id");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		account.setId(accountId);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			boolean isDeleted = ams.deleteAccount(account);
			if (isDeleted) {
				out.println("<h1> Account Successfully Deleted </h1>");
				request.getRequestDispatcher("deleteAccount.html").include(request, response);
			}
		} catch (PecuniaException | AccountException e) {
			out.println("<h1>Failure</h1><br>");
			request.getRequestDispatcher("deleteAccount.html").include(request, response);

		}

	}

}
