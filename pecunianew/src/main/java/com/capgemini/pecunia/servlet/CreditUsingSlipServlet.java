package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;

public class CreditUsingSlipServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		String accountId = request.getParameter("accountNumber");
		double amount = Double.parseDouble(request.getParameter("creditSlipAmount"));

		Transaction creditSlip = new Transaction();
		creditSlip.setAccountId(accountId);
		creditSlip.setAmount(amount);
		TransactionService trans = new TransactionServiceImpl();
		
		try {
			int transId = trans.creditUsingSlip(creditSlip);
		
			request.getRequestDispatcher("creditUsingSlip.html").include(request, response);
			out.println("<script>");
			out.println("$('#success-toast-body').html('Amount has been credited. Transaction id is \t" + transId + "');");
			out.println("$('#id-generation-success').toast('show');");
			out.println("</script>");
		} catch (TransactionException | PecuniaException e) {
			

			request.getRequestDispatcher("creditUsingSlip.html").include(request, response);
			out.println("<script>$('#id-generation-failure').toast('show');</script>");
		}
	}
}
