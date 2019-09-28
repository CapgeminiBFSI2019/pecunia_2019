package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.*;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;


public class LoanDisbursedPI {
	public static void main(String[] args) throws LoanDisbursalException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		ArrayList<Loan> approvedLoanRequests = new ArrayList<Loan>();
		ArrayList<Loan> rejectedLoanRequests = new ArrayList<Loan>();
		ArrayList<LoanDisbursal> loanDisbursedData = new ArrayList<LoanDisbursal>();
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
        
		System.out.println("press 1 to retrieve loan requests");
		
		int choice = sc.nextInt();
		
		switch(choice){
		case 1: 
			try {
				retrievedLoanRequests = loanDisbursalService.retrieveAll();
				System.out.println(retrievedLoanRequests);
			} catch (PecuniaException | IOException | LoanDisbursalException e) {
				// TODO Auto-generated catch block
				throw new LoanDisbursalException(e.getMessage());
			}
			
			break;
			
			default:
				System.out.println("Invalid choice");
		}
		
		
		System.out.println("press 1 to accept/reject loan requests");
		int choice1 = sc.nextInt();
		
		switch(choice1){
		case 1: 
			try {
				
				System.out.println("Approved loan requests");
				approvedLoanRequests = loanDisbursalService.approveLoan(retrievedLoanRequests);
				System.out.println(approvedLoanRequests);
				System.out.println("Rejected loan requests");
				rejectedLoanRequests = loanDisbursalService.rejectedLoanRequests();
				System.out.println(rejectedLoanRequests);
				loanDisbursalService.updateLoanStatus(rejectedLoanRequests,approvedLoanRequests);
			} catch (PecuniaException | IOException | LoanDisbursalException e) {
				// TODO Auto-generated catch block
				throw new LoanDisbursalException(e.getMessage());
			}
			
			break;
			
			default:
				System.out.println("Invalid choice");
		}
		
		System.out.println("press 1 to retrieve the data from loan disbursed database");
		int choice2 = sc.nextInt();
		

		switch(choice2){
		case 1: 
			try {
				loanDisbursedData  = loanDisbursalService.approvedLoanList();
				
				
			} catch (PecuniaException | IOException e) {
				// TODO Auto-generated catch block
				throw new LoanDisbursalException(e.getMessage());
			}
			
			break;
			
			default:
				System.out.println("Invalid choice");
		}
		
		System.out.println("press 1 to update the existing balance of account");
		int choice3 = sc.nextInt();
		
		switch(choice3){
		case 1: 
			
			try {
				loanDisbursalService.updateExistingBalance(approvedLoanRequests);
			} catch (PecuniaException | TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
			
			default:
				System.out.println("Invalid choice");
		}
		
		
	
		
	}
}
