package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		String update;
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		
		while (true) {
			System.out.println(
					"press" + "\n" + " 1. to retrieve loan requests" + "\n" + "2. to accept/reject loan requests" + "\n"
							+ "3. to retrieve the data from loan disbursed database" + "\n"
							+ "4. to update the existing balance of account" + "\n" + "0. to exit");

			int choice = sc.nextInt();

			if (choice == 1) {

				try {
					retrievedLoanRequests = loanDisbursalService.retrieveAll();
					System.out.println(retrievedLoanRequests);
				} catch (PecuniaException | IOException | LoanDisbursalException e) {
					throw new LoanDisbursalException(e.getMessage());
				}

			}

			else if (choice == 2) {

				try {

					approvedLoanRequests = loanDisbursalService.approveLoan(retrievedLoanRequests);
					if (approvedLoanRequests.size() == 0)
						System.out.println("No approved loan requests");
					else {
						System.out.println("Approved loan requests");
						System.out.println(approvedLoanRequests);
					}
					if (rejectedLoanRequests.size() == 0)
						System.out.println("No rejected loan requests");
					else {
						System.out.println("Rejected loan requests");
						rejectedLoanRequests = loanDisbursalService.rejectedLoanRequests();
						System.out.println(rejectedLoanRequests);
					}
					if(approvedLoanRequests.size() == 0 && rejectedLoanRequests.size() == 0)
						System.out.println("No loan requests");
					update = loanDisbursalService.updateLoanStatus(rejectedLoanRequests, approvedLoanRequests);
					System.out.println(update);
				} catch (PecuniaException | IOException | LoanDisbursalException e) {
					throw new LoanDisbursalException(e.getMessage());
				}

			}

			else if (choice == 3) {
				try {
					loanDisbursedData = loanDisbursalService.approvedLoanList();

				} catch (PecuniaException | IOException e) {
					throw new LoanDisbursalException(e.getMessage());
				}

			}

			else if (choice == 4) {

				try {
					loanDisbursalService.updateExistingBalance(approvedLoanRequests);
				} catch (PecuniaException | TransactionException e) {
					throw new LoanDisbursalException(e.getMessage());
				}

			}

			else if (choice == 0) {

				System.exit(1);

			}

			else {
				System.out.println("INVALID CHOICE");
			}

		}
	}

}
