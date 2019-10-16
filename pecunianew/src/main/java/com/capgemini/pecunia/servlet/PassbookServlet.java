package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.javafx.collections.MappingChange.Map;

public class PassbookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
		} catch (Exception e) {
		}
		HashMap<String, String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();

		myMap = objectMapper.readValue(jb.toString(), HashMap.class);
		String accountId = (String) myMap.get("accountID");
		Account accountObject = new Account();
		accountObject.setId(accountId);
		List<Transaction> updatePassbook;

		PassbookMaintenanceService passbookService = new PassbookMaintenanceServiceImpl();
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result = false;
		PrintWriter out = response.getWriter();
		try {
			updatePassbook = passbookService.updatePassbook(accountId);
			String Id = request.getParameter("accountID");
			System.out.println("Account ID - " + Id);
			if (result) {
				((ObjectNode) dataResponse).put("success", result);
			} else {
				throw new PecuniaException(ErrorConstants.UPDATE_PASSBOOK_ERROR);
			}

		} catch (PecuniaException | PassbookException e) {
			((ObjectNode) dataResponse).put("success", result);
			((ObjectNode) dataResponse).put("message", e.getMessage());

		} finally {
			out.print(dataResponse);
		}

	}
}