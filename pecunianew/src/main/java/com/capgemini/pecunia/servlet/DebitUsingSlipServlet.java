package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;

public class DebitUsingSlipServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("accountNumber");
		double amount = Double.parseDouble(request.getParameter("debitSlipAmount"));

		Transaction debitSlip = new Transaction();
		debitSlip.setAccountId(accountId);
		debitSlip.setAmount(amount);
		TransactionService trans = new TransactionServiceImpl();

		try {
			int transId = trans.debitUsingSlip(debitSlip);
			PrintWriter out = response.getWriter();
			out.println("<h1>Transaction Id is: </h1>" + transId);
			out.println("<h1>Transaction Successful</h1>");
		} catch (TransactionException | PecuniaException e) {
			PrintWriter out = response.getWriter();
			out.println("<h1>Failure</h1><br>" + e.getMessage());
		}
	}
}

