package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;

public class LoanDisbursalBalanceUpdateServlet extends HttpServlet {


	private static final long serialVersionUID = 2212946981490266552L;
	

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAccepted = new ArrayList<Loan>();
		
		
		try {
			retrieveAccepted = loanDisbursalService.approveLoanWithoutStatus();
		} catch (PecuniaException | LoanDisbursalException e) {
		
			retrieveAccepted = null;
		}
	
		String res = request.getParameter("update-account-balance");
		if (res.equals("Yes")) {
			try {
				
				String msg = loanDisbursalService.updateExistingBalance(retrieveAccepted);
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#success-toast-body').html('" + msg + "');");
                out.println("$('#loan-disbursal-success').toast('show');");
                out.println("</script>");
			} catch (PecuniaException | LoanDisbursalException | TransactionException e) {

				
				String msg = "Error occured";
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#failure-toast-body').html('" + msg + "');");
                out.println("$('#loan-disbursal-failure').toast('show');");
                out.println("</script>");
			}
		}
	}

}
