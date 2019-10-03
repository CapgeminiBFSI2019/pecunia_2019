package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;

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

		String htmlRespone = "<html>";

		htmlRespone += "<h2>Your username is: " + name + "</h2>";
		htmlRespone += "<h2>Your gender is: " + gender + "</h2>";

		htmlRespone += "</html>";

		writer.println(htmlRespone);
	}
	try {
		int transId = trans.debitUsingCheque(debitChequeTransaction, debitCheque);
		PrintWriter out = response.getWriter();
		out.println("<h1>Transaction Id is: </h1>" + transId);
		out.println("<h1>Transaction Successful</h1>");
	} catch (TransactionException | PecuniaException e) {
		PrintWriter out = response.getWriter();
		out.println("<h1>Failure</h1><br>" + e.getMessage());
	}
}
