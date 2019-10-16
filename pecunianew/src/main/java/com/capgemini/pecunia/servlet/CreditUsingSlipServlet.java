package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreditUsingSlipServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {  }
		Map<String,String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();
		
		myMap = objectMapper.readValue(jb.toString(), HashMap.class);
		
		String accountId = myMap.get("accountNumber");
		double amount = Double.parseDouble(myMap.get("creditSlipAmount"));
		
		HttpSession session = request.getSession(false);
		JsonNode dataResponse = objectMapper.createObjectNode();
//		if (session == null) {
//		    // Session is not created.
////			response.sendRedirect("session.html");
//			((ObjectNode) dataResponse).put("success", false);
//			((ObjectNode) dataResponse).put("message", "Session has expired");
//			out.print(dataResponse);
//			return;
//		}
		
		
//		String accountId = request.getParameter("accountNumber");
//		double amount = Double.parseDouble(request.getParameter("creditSlipAmount"));

		
		Transaction creditSlip = new Transaction();
		creditSlip.setAccountId(accountId);
		creditSlip.setAmount(amount);
		TransactionService trans = new TransactionServiceImpl();
		
		try {
			int transId = trans.creditUsingSlip(creditSlip);
			((ObjectNode) dataResponse).put("success", true);
			((ObjectNode) dataResponse).put("transaction_id", transId);
			((ObjectNode) dataResponse).put("message", "Amount has been credited. Transaction id is " + transId);
//			request.getRequestDispatcher("creditUsingSlip.html").include(request, response);
//			out.println("<script>");
//			out.println("$('#success-toast-body').html('Amount has been credited. Transaction id is \t" + transId + "');");
//			out.println("$('#id-generation-success').toast('show');");
//			out.println("</script>");
		} catch (TransactionException | PecuniaException e) {
			((ObjectNode) dataResponse).put("success", false);
			((ObjectNode) dataResponse).put("message", e.getMessage());
//			request.getRequestDispatcher("creditUsingSlip.html").include(request, response);
//			out.println("<script>$('#id-generation-failure').toast('show');</script>");
		}
		finally {
			out.print(dataResponse);
		}
	}
}
