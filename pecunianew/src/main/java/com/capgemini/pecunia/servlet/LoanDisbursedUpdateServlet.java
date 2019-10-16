package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;


public class LoanDisbursedUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = -5955186616558309852L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ArrayList<Loan> retrieveAccepted = new ArrayList<Loan>();
		ArrayList<Loan> retrieveRejected = new ArrayList<Loan>();
		
		try {
			retrieveAccepted = loanDisbursalService.approveLoanWithoutStatus();
		} catch (PecuniaException | LoanDisbursalException e) {
		
			retrieveAccepted = null;
		}
		
		try {
			retrieveRejected = loanDisbursalService.rejectedLoanRequests();
		} catch (PecuniaException | LoanDisbursalException e) {
			retrieveRejected = null;
		}
		

			try {
				loanDisbursalService.updateLoanStatus(retrieveRejected, retrieveAccepted);
				String msg = "Status has been updated";
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#success-toast-body').html('" + msg + "');");
                out.println("$('#loan-disbursal-success').toast('show');");
                out.println("</script>");
			} catch (PecuniaException | LoanDisbursalException e) {

				
				String msg = "Status been already updated";
				request.getRequestDispatcher("loanDisbursal.html").include(request, response);
				out.println("<script>");
                out.println("$('#failure-toast-body').html('" + msg + "');");
                out.println("$('#loan-disbursal-failure').toast('show');");
                out.println("</script>");
			}
		}
	}

