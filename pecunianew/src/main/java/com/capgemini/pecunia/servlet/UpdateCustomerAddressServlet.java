package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

/**
 * Servlet implementation class UpdateCustomerAddressServlet
 */
public class UpdateCustomerAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		String accountId = request.getParameter("account-id");

		String line1 = request.getParameter("address-line1");
		String line2 = request.getParameter("address-line2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String zipcode = request.getParameter("zipcode");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Account account = new Account();
		Address address = new Address();
		account.setId(accountId);
		address.setLine1(line1);
		address.setLine2(line2);
		address.setCity(city);
		address.setCountry(country);
		address.setState(state);
		address.setZipcode(zipcode);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			boolean updated = ams.updateCustomerAddress(account, address);
			if (updated) {
				request.getRequestDispatcher("updateAddress.html").include(request, response);
				out.println("<script>$('#update-address-success').toast('show');</script>");
			}
		} catch (PecuniaException | AccountException e) {
			request.getRequestDispatcher("updateAddress.html").include(request, response);
			out.println("<script>$('#update-address-failure').toast('show');</script>");
		}
	}

}
