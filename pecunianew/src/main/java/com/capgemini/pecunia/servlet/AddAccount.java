package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAccount
 */
public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
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
//		double accountbalance = request.getParameter(accountbalance);
//
//		double accountinterest = request.getParameter(accountinterest);


}
}
