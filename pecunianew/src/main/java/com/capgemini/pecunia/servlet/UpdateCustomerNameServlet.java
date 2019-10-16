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

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.capgemini.pecunia.util.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class UpdateCustomerNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		
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
		String accountId = myMap.get("accountId");

		String custName = myMap.get("name");
		
		Account account = new Account();
		Customer customer = new Customer();
		account.setId(accountId);
		customer.setName(custName);
		
		
		AccountManagementService ams = new AccountManagementServiceImpl();
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean updated=false;
		PrintWriter out =response.getWriter();

		try {
			updated = ams.updateCustomerName(account, customer);
			if (updated) {
				((ObjectNode) dataResponse).put("success", updated);
				((ObjectNode) dataResponse).put("message", "Update Successful");
//				out.println("<script>$('#update-name-success').toast('show');</script>");
			}
		} catch (PecuniaException | AccountException e) {
			((ObjectNode) dataResponse).put("failure", updated);
			((ObjectNode) dataResponse).put("message", e.getMessage());
//			request.getRequestDispatcher("updateName.html").include(request, response);
//			out.println("<script>$('#update-name-failure').toast('show');</script>");

		}
		finally {
			out.print(dataResponse);
		}

	}

}
