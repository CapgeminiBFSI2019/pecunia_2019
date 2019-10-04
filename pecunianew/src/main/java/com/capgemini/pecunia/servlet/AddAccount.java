package com.capgemini.pecunia.servlet;

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


public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateofbirth = request.getParameter("dateofbirth");

		String contact = request.getParameter("contact");

		String addressline1 = request.getParameter("addressline1");
		String addressline2 = request.getParameter("addressline2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String zipcode = request.getParameter("zipcode");
		String aadhar = request.getParameter("aadhar");
		String pan = request.getParameter("pan");

		String accounttype = request.getParameter("accounttype");
		String branchid = request.getParameter("branchid");
		double accountbalance = Double.parseDouble(request.getParameter("accountbalance"));

		double accountinterest = Double.parseDouble(request.getParameter("accountinterest"));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		Address address = new Address();
		Customer customer = new Customer();

		address.setLine1(addressline1);
		address.setLine2(addressline2);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipcode(zipcode);
		account.setAccountType(accounttype);
		account.setBranchId(branchid);
		account.setBalance(accountbalance);
		account.setInterest(accountinterest);
		customer.setAadhar(aadhar);
		customer.setContact(contact);
		customer.setDob(LocalDate.parse(dateofbirth, dateTimeFormatter));
		customer.setGender(gender);
		customer.setName(name);
		customer.setPan(pan);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			String created = ams.addAccount(customer, address, account);
			if (created != null) {
				out.println("<h1> Account successfully created </h1>");

				request.getRequestDispatcher("addAccount.html").include(request, response);
			}
		} catch (PecuniaException | AccountException e) {
			out.println("<h1>Failure</h1><br>");
			request.getRequestDispatcher("addAccount.html").include(request, response);
		}
	}

}
