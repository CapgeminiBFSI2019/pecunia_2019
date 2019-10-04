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

/**
 * Servlet implementation class CreditUsingCheque
 */
public class CreditUsingCheque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreditUsingCheque() {
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Transaction creditTransaction = new Transaction();
		Cheque creditCheque = new Cheque();
		
		String payeeAccountNumber = request.getParameter("payeeAccountNumber");
		String beneficiaryAccountNumber = request.getParameter("beneficiaryAccountNumber");
		String chequeNumber = request.getParameter("creditChequeNumber");
		String payeeName = request.getParameter("payeeName");
		double amount = Double.parseDouble(request.getParameter("creditChequeAmount"));
		LocalDate chequeIssueDate = LocalDate.parse(request.getParameter("creditChequeIssueDate"));
		String bankName = request.getParameter("bankName");
		String ifsc = request.getParameter("payeeIfsc");
		
		creditTransaction.setAmount(amount);
		creditTransaction.setAccountId(beneficiaryAccountNumber);
		creditTransaction.setTransTo(beneficiaryAccountNumber);
		creditTransaction.setTransFrom(payeeAccountNumber);
		
		creditCheque.setAccountNo(payeeAccountNumber);
		creditCheque.setHolderName(payeeName);
		creditCheque.setIfsc(ifsc);
		creditCheque.setIssueDate(chequeIssueDate);
		creditCheque.setNum(Integer.parseInt(chequeNumber));
		creditCheque.setBankName(bankName);
		
		
		TransactionService trans = new TransactionServiceImpl();
		
		try {
			int transId = trans.creditUsingCheque(creditTransaction, creditCheque);
			PrintWriter out = response.getWriter();
			out.println("<h1>Transaction Id is: </h1>" + transId);
			out.println("<h1>Transaction Successful</h1>");
		} catch (TransactionException | PecuniaException e) {
			PrintWriter out = response.getWriter();
			out.println("<h1>Failure</h1><br>" + e.getMessage());
		}
	}

}
