package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.service.LoanService;
import com.capgemini.pecunia.service.LoanServiceImpl;

/**
 * Servlet implementation class LoanRequest1
 */
public class LoanRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		String AccountId=request.getParameter("AccountId");
		double amount=Double.parseDouble(request.getParameter("LoanAmount"));
		int tenure=Integer.parseInt(request.getParameter("Tenure"));
		double roi=Double.parseDouble(request.getParameter("roi"));
		int creditScore=Integer.parseInt(request.getParameter("CreditScore"));
		String loanStatus=request.getParameter("status");
		String loanType=request.getParameter("LoanType");
		Loan loan=new Loan();
		loan.setAccountId(AccountId);
		loan.setAmount(amount);
		loan.setTenure(tenure);
		loan.setCreditScore(creditScore);
		loan.setLoanStatus(loanStatus);
		loan.setRoi(roi);
		loan.setType(loanType);
		LoanService loanService=new LoanServiceImpl();
		double emi=loanService.calculateEMI(amount, tenure, roi);
		loan.setEmi(emi);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			boolean isSuccess=loanService.createLoanRequest(loan);
			if(isSuccess) {
				out.println("<h3 class='text-success'>Loan request data added successfully!!</h3>");
				request.getRequestDispatcher("LoanRequestServlet.html").include(request, response); 
				
			}
		} catch (LoanException e) {
			out.println("<h3 class='text-danger'>Failure, please try again!!</h3>");
			request.getRequestDispatcher("LoanRequest.html").include(request, response); 
			
		}
		
		
	}

	}


