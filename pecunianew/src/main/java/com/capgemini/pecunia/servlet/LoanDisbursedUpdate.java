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
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;


public class LoanDisbursedUpdate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5955186616558309852L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAccepted = new ArrayList<Loan>();
		ArrayList<Loan> retrieveRejected = new ArrayList<Loan>();
		
		try {
			retrieveAccepted = loanDisbursalService.approveLoan();
		} catch (PecuniaException | LoanDisbursalException e) {
		
			retrieveAccepted = null;
		}
		
		try {
			retrieveRejected = loanDisbursalService.rejectedLoanRequests();
		} catch (PecuniaException | LoanDisbursalException e) {
			retrieveRejected = null;
		}
		
		
		String res = request.getParameter("update-account-balance");
		if (res.equals("Yes")) {
			try {
				String status = loanDisbursalService.updateLoanStatus(retrieveRejected, retrieveAccepted);
//				request.getRequestDispatcher("loanDisbursal.html").include(request,response);
//				out.write(status);
				String msg = "Accounts has been updated";
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#success-toast-body').html('" + msg + "');");
                out.println("$('#loan-success-failure').toast('show');");
                out.println("</script>");
			} catch (PecuniaException | LoanDisbursalException e) {
//				request.getRequestDispatcher("loanDisbursal.html").include(request,response);
//				out.write("Not updated. Either no request is pending or connection error");
				
				String msg = "No account has been updated";
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#failure-toast-body').html('" + msg + "');");
                out.println("$('#loan-disbursal-failure').toast('show');");
                out.println("</script>");
			}
		}
	}
}
