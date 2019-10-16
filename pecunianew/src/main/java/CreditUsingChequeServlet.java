

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;

/**
 * Servlet implementation class CreditUsingChequeServlet
 */
public class CreditUsingChequeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null) {
		    // Session is not created.
			response.sendRedirect("session.html");
		}
		
		Transaction creditTransaction = new Transaction();
		Cheque creditCheque = new Cheque();
		String payeeAccountNumber = request.getParameter("payeeAccountNumber");
		String beneficiaryAccountNumber = request.getParameter("beneficiaryAccountNumber");
		String chequeNumber = request.getParameter("creditChequeNumber");
		String payeeName = request.getParameter("payeeName");
		double amount = Double.parseDouble(request.getParameter("creditChequeAmount"));
		LocalDate chequeIssueDate = LocalDate.parse(request.getParameter("creditChequeIssueDate"));
		String bankName = request.getParameter("bankName");
		String ifsc = request.getParameter("payeeIfsc");
		
		creditTransaction.setAmount(amount);
		creditTransaction.setAccountId(beneficiaryAccountNumber);
		creditTransaction.setTransTo(beneficiaryAccountNumber);
		creditTransaction.setTransFrom(payeeAccountNumber);
		
		creditCheque.setAccountNo(payeeAccountNumber);
		creditCheque.setHolderName(payeeName);
		creditCheque.setIfsc(ifsc);
		creditCheque.setIssueDate(chequeIssueDate);
		creditCheque.setNum(Integer.parseInt(chequeNumber));
		creditCheque.setBankName(bankName);
		
		
		TransactionService trans = new TransactionServiceImpl();
		try {
			int transId = trans.creditUsingCheque(creditTransaction, creditCheque);
			
			request.getRequestDispatcher("creditUsingCheque.html").include(request, response);
			out.println("<script>");
			out.println("$('#success-toast-body').html('Amount has been credited. Transaction id is \t" + transId + "');");
			out.println("$('#id-generation-success').toast('show');");
			out.println("</script>");
		} catch (TransactionException | PecuniaException e) {
			request.getRequestDispatcher("creditUsingCheque.html").include(request, response);
			out.println("<script>$('#id-generation-failure').toast('show');</script>");
		}
	}

}
