package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		
		String accountId = request.getParameter("account-id");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		account.setId(accountId);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			boolean isDeleted = ams.deleteAccount(account);
			if (isDeleted) {
				request.getRequestDispatcher("deleteAccount.html").include(request, response);
				out.println("<script>$('#delete-success').toast('show');</script>");
			}
		} catch (PecuniaException | AccountException e) {
			request.getRequestDispatcher("deleteAccount.html").include(request, response);
			out.println("<script>$('#delete-failure').toast('show');</script>");
			
		}

	}

}
