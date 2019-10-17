package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DebitUsingSlipServlet extends HttpServlet {

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

		JsonObject dataResponse = new JsonObject();

		Gson gson = new Gson();
		JsonElement jelem = gson.fromJson(jb.toString(), JsonElement.class);
		JsonObject jobj = jelem.getAsJsonObject();


		String accountId = jobj.get("accountNumber").getAsString();
		double amount = Double.parseDouble(jobj.get("debitSlipAmount").toString());

		HttpSession session = request.getSession(false);

//		if (session == null) {
//			// Session is not created.
//			dataResponse.addProperty("success", false);
//			dataResponse.addProperty("message", "Session has expired");
//
//			out.print(dataResponse);
//			return;
//		}


		Transaction debitSlip = new Transaction();
		
		
		debitSlip.setAccountId(accountId);
		debitSlip.setAmount(amount);
		
		TransactionService trans = new TransactionServiceImpl();
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
//		Object session = null;
//		if (session == null) {
//			// Session is not created.
//			dataResponse.addProperty("success", false);
//			dataResponse.addProperty("message", "Session has expired");
//
//			out.print(dataResponse);
//			return;
//		}

//		try {
//			
//			int transId = trans.creditUsingSlip(creditSlip);
//			dataResponse.addProperty("success", true);
//			dataResponse.addProperty("transaction_id", transId);
//			dataResponse.addProperty("message", "Amount has been credited. Transaction id is " + transId);
//
//		} catch (TransactionException | PecuniaException e) {
//			dataResponse.addProperty("success", false);
//			dataResponse.addProperty("message", e.getMessage());
//		} finally {
//			out.print(dataResponse);
//		}
//	}
//}
		try {
			int transId = trans.debitUsingSlip(debitSlip);
			
				dataResponse.addProperty("success", true);
				dataResponse.addProperty("Account Id", transId);
				dataResponse.addProperty("message", "Amount debited.Trans Id is \t" + transId);
//				request.getRequestDispatcher("addAccount.html").include(request, response);
//				out.println("<script>");
//				out.println("$('#success-toast-body').html('Account created successfully. Account id is \t" + created + "');");
//				out.println("$('#add-account-success').toast('show');");
//				out.println("</script>");
			
		} catch (PecuniaException | TransactionException e) {
			dataResponse.addProperty("success", false);
			dataResponse.addProperty("message", e.getMessage());

		
//			request.getRequestDispatcher("addAccount.html").include(request, response);
//			out.println("<script>$('#add-account-failure').toast('show');</script>");

		} finally {
			out.print(dataResponse);
		}

	}

}


