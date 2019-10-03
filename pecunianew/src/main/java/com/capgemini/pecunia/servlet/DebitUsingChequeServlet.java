package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;

public class DebitUsingChequeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("accountNumber");
		double amount = Double.parseDouble(request.getParameter("debitChequeAmount"));
		String accountHolderName = request.getParameter("accountHolderName");
		String ifsc = request.getParameter("bankIfsc");
		int chequeNumber = Integer.parseInt(request.getParameter("chequeNumber"));
		LocalDate issueDate = LocalDate.parse(request.getParameter("chequeIssueDate"));
		
		System.out.println("Servelet cheque date : "+issueDate);
		
		Transaction debitChequeTransaction = new Transaction();
		Cheque debitCheque = new Cheque();
		debitChequeTransaction.setAccountId(accountId);
		debitChequeTransaction.setAmount(amount);
		debitCheque.setAccountNo(accountId);
		debitCheque.setHolderName(accountHolderName);
		debitCheque.setIfsc(ifsc);
		debitCheque.setIssueDate(issueDate);
		debitCheque.setNum(chequeNumber);
		TransactionService trans = new TransactionServiceImpl();
		
		try {
			int transId = trans.debitUsingCheque(debitChequeTransaction, debitCheque);
			PrintWriter out = response.getWriter();
			out.println("<h1>Transaction Id is: </h1>" + transId);
			out.println("<h1>Transaction Successful</h1>");
		} catch (TransactionException | PecuniaException e) {
			PrintWriter out = response.getWriter();
			out.println("<h1>Failure</h1><br>" + e.getMessage());
		}
}
}
