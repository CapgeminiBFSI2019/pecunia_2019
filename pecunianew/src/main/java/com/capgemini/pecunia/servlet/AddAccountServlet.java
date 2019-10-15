package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class AddAccountServlet
 */
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		Account account = new Account();
		Address address = new Address();
		Customer customer = new Customer();
		String name = myMap.get("name");
		String gender = myMap.get("gender");
		if ("Female".equalsIgnoreCase(gender)) {
			customer.setGender("F");
		} else
			customer.setGender("M");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateofbirth = myMap.get("dateofbirth");

		String contact = myMap.get("contact");

		String addressline1 = myMap.get("addressline1");
		String addressline2 = myMap.get("addressline2");
		String city = myMap.get("city");
		String state = myMap.get("state");
		String country = myMap.get("country");
		String zipcode = myMap.get("zipcode");
		String aadhar = myMap.get("aadhar");
		String pan = myMap.get("pan");

		String accounttype = myMap.get("accounttype");
		String branchid = myMap.get("branchid");
		double accountbalance = Double.parseDouble(myMap.get("accountbalance"));

		double accountinterest = Double.parseDouble(myMap.get("accountinterest"));

		address.setLine1(addressline1);
		address.setLine2(addressline2);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipcode(zipcode);
		customer.setAadhar(aadhar);
		customer.setContact(contact);
		customer.setDob(LocalDate.parse(dateofbirth, dateTimeFormatter));
		customer.setName(name);
		customer.setPan(pan);
		account.setAccountType(accounttype);
		account.setBranchId(branchid);
		account.setBalance(accountbalance);
		account.setInterest(accountinterest);

		AccountManagementService ams = new AccountManagementServiceImpl();
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean updated = false;
		PrintWriter out = response.getWriter();
		try {
			String created = ams.addAccount(customer, address, account);
			if (created != null) {
				((ObjectNode) dataResponse).put("success", updated);
				((ObjectNode) dataResponse).put("message", "Update Successful");
//				request.getRequestDispatcher("addAccount.html").include(request, response);
//				out.println("<script>");
//				out.println("$('#success-toast-body').html('Account created successfully. Account id is \t" + created + "');");
//				out.println("$('#add-account-success').toast('show');");
//				out.println("</script>");
			}
		} catch (PecuniaException | AccountException e) {
			((ObjectNode) dataResponse).put("failure", updated);
			((ObjectNode) dataResponse).put("message", e.getMessage());
//			request.getRequestDispatcher("addAccount.html").include(request, response);
//			out.println("<script>$('#add-account-failure').toast('show');</script>");

		} finally {
			out.print(dataResponse);
		}

	}

}
