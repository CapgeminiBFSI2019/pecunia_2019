package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class AddAccountServlet
 */
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		Account account = new Account();
		Address address = new Address();
		Customer customer = new Customer();

		String name = jobj.get("name").getAsString();

		String gender = jobj.get("gender").getAsString();
		if ("Female".equalsIgnoreCase(gender)) {
			customer.setGender("F");
		} else
			customer.setGender("M");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateofbirth = jobj.get("dateofbirth").getAsString();

		String contact = jobj.get("contact").getAsString();

		String addressline1 = jobj.get("addressline1").getAsString();
		String addressline2 = jobj.get("addressline2").getAsString();
		String city = jobj.get("city").getAsString();
		String state = jobj.get("state").getAsString();
		String country = jobj.get("country").getAsString();
		String zipcode = jobj.get("zipcode").getAsString();
		String aadhar = jobj.get("aadhar").getAsString();
		String pan = jobj.get("pan").getAsString();

		String accounttype = jobj.get("accounttype").getAsString();
		String branchid = jobj.get("branchid").getAsString();
		double accountbalance = Double.parseDouble(jobj.get("accountbalance").getAsString());

		double accountinterest = Double.parseDouble(jobj.get("accountinterest").getAsString());

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

//		PrintWriter out = response.getWriter();

		try {
			String created = ams.addAccount(customer, address, account);
			if (created != null) {
				dataResponse.addProperty("success", true);
//				request.getRequestDispatcher("addAccount.html").include(request, response);
//				out.println("<script>");
//				out.println("$('#success-toast-body').html('Account created successfully. Account id is \t" + created + "');");
//				out.println("$('#add-account-success').toast('show');");
//				out.println("</script>");
			}
		} catch (PecuniaException | AccountException e) {
			dataResponse.addProperty("success", false);
			dataResponse.addProperty("message", e.getMessage());

			String created=null;
			dataResponse.addProperty("Account Id", created);
			dataResponse.addProperty("message", "Account has been created. Account Id is \t" + created);
//			request.getRequestDispatcher("addAccount.html").include(request, response);
//			out.println("<script>$('#add-account-failure').toast('show');</script>");

		} finally {
			out.print(dataResponse);
		}

	}

}
