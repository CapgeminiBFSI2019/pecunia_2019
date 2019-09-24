package com.capgemini.pecunia.pl;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.service.LoanService;
import com.capgemini.pecunia.service.LoanServiceImpl;

public class LoanMain {
	static Scanner sc = new Scanner(System.in);
	static LoanServiceImpl loanService = null;
	static LoanServiceImpl loanServiceImpl = null;
	static Logger logger = Logger.getRootLogger();

}
public static void main(String[] args) {
	PropertyConfigurator.configure("resources//log4j.properties");
	Loan loan = null;

	//String donorId = null;
	int option = 0;

	while (true) {

		// show menu
		System.out.println();
		System.out.println();
		System.out.println(" Welcome to Loan Services ");
		System.out.println("_______________________________\n");

		System.out.println("1.Add Loan Details ");
		System.out.println("2.View Loan Details");
		System.out.println("3.Retrive All");
		System.out.println("4.Exit");
		System.out.println("________________________________");
		System.out.println("Select an option:");
		// accept option

		try {
			option = sc.nextInt();

			switch (option) {

			case 1:

				while (loan== null) {
					donorBean = populateDonorBean();
					// System.out.println(donorBean);
				}

				try {
					loanService = new LoanServiceImpl();
					//loanId = loanService.addDonorDetails(loan);

					System.out
							.println("Loan details  has been successfully registered ");
					System.out.println("Loan  ID Is: " + loan_Id);

				} catch (LoanException loanException) {
					logger.error("exception occured", loanException);
					System.out.println("ERROR : "
							+ loanException.getMessage());
				} finally {
					//loanId = null;
					loanService = null;
					loan = null;
				}

				break;

			case 2:

				loanServiceImpl = new LoanServiceImpl();

				System.out.println("Enter numeric donor id:");
				loanId = sc.next();

				while (true) {
					if (loanServiceImpl.validateDonorId(loanId)) {
						break;
					} else {
						System.err
								.println("Please enter numeric loan id only, try again");
						loanId = sc.next();
					}
				}

				loan= getDonorDetails(loanId);

				if (loan != null) {
					System.out.println("AccountId               :"
							+ loan.getAccountId());
					System.out.println("Loan Amount              :"
							+ loan.getAmount());
					System.out.println("Type of Loan  :"
							+ loan.getType());
					System.out.println("Tenure of Loan       :"
							+ loan.getTenure());
					System.out.println("EMI              :"
							+ loan.getEmi());
					System.out.println("Credit Score               :"
							+ loan.getCreditScore());
					System.out.println("Loan Status          :"
							+ loan.getLoanStatus());
					System.out.println("Rate of Interest     :"
							+ loan.getRoi());
				} else {
					System.err
							.println("There are no loan details associated with this account id "
									+ accountId);
				}

				break;

//			case 3:
//
//				loanService = new LoanServiceImpl();
//				try {
//					List<Laon> donorList = new ArrayList<DonorBean>();
//					donorList = donorService.retriveAll();
//
//					if (donorList != null) {
//						Iterator<DonorBean> i = donorList.iterator();
//						while (i.hasNext()) {
//							System.out.println(i.next());
//						}
//					} else {
//						System.out
//								.println("Nobody has made a donation, yet.");
//					}
//
//				}
//
//				catch (DonorException e) {
//
//					System.out.println("Error  :" + e.getMessage());
//				}
//
//				break;

			case 4:

				System.out.print("Exit Trust Application");
				System.exit(0);
				break;
			default:
				System.out.println("Enter a valid option[1-4]");
			}// end of switch
		}

		catch (InputMismatchException e) {
			sc.nextLine();
			System.err.println("Please enter a numeric value, try again");
		}

	}// end of while
}
}// end of try