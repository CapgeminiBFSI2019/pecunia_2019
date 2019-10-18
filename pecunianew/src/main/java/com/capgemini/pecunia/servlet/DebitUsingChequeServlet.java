package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DebitUsingChequeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		Gson gson = new Gson();
		JsonElement jelem = gson.fromJson(jb.toString(), JsonElement.class);
		JsonObject jobj = jelem.getAsJsonObject();

		String accountId = jobj.get("accountNumber").getAsString();
		double amount = Double.parseDouble(jobj.get("debitChequeAmount").getAsString());
		String accountHolderName = jobj.get("holderName").getAsString();
		String ifsc = jobj.get("ifsc").getAsString();
		int chequeNumber = Integer.parseInt(jobj.get("debitChequeNumber").getAsString());
		LocalDate issueDate = LocalDate.parse(jobj.get("issueDate").getAsString());

		HttpSession session = request.getSession(false);

		JsonObject dataResponse = new JsonObject();
		// if (session == null) {
		// // Session is not created.
		// response.sendRedirect("session.html");
		// }
		//

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
			dataResponse.addProperty("success", true);
			dataResponse.addProperty("transaction_id", transId);
			dataResponse.addProperty("message", "Amount has been debited. Transaction id is " + transId);
		} catch (TransactionException | PecuniaException e) {

			dataResponse.addProperty("success", false);
			dataResponse.addProperty("message", e.getMessage());
		} finally {
			out.print(dataResponse);
		}

	}
	
	
	@Override
	public void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {    
	    
	   String reqOrigin = request.getHeader("Origin");
	   if(reqOrigin == null) {
		   reqOrigin = "*";
	   }
	    response.setHeader("Access-Control-Allow-Origin",reqOrigin);
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
}
}
