	package com.capgemini.pecunia.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;
import com.capgemini.pecunia.service.LoanService;
import com.capgemini.pecunia.service.LoanServiceImpl;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.capgemini.pecunia.util.Constants;

public class PecuniaMain {
	public static void main(String[] args) throws IOException, LoanDisbursalException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		int choice1 = 0, choice2 = 0, choice3 = 0;
		System.out.println("Enter your option: " + "\n1. Login" + "\n2. Exit");
		choice1 = scanner.nextInt();
		switch (choice1) {
		case 1:
			// validateLogin
			// if validated
			// Case true
			boolean loginFlag = false;
			do {
				System.out.print("Enter username : ");
				String username = scanner.next();
				System.out.print("Enter password : ");
				String password = scanner.next();
				loginFlag = login(username, password);
				if (loginFlag) {
					System.out.println("Login successful");
					break;
				} else {
					System.out.println("Login Unsuccessful");
					System.out.print("Do you want to continue Y / N :-");
					String choice = scanner.next();
					if (choice.equalsIgnoreCase("n")) {
						break;
					} else if (!choice.equalsIgnoreCase("y")) {
						System.out.println("Invalid choice");
					}
				}
			} while (!loginFlag);
			String flag = "y";
			while (flag.equalsIgnoreCase("y")) {
				System.out.println("Enter your option: " + "\n1. Create Account" + "\n2. Update Account"
						+ "\n3. Delete Existing Account" + "\n4. Update Passbook/ Account Summary"
						+ "\n5. Transaction" + "\n6. Loan Management");
				choice2 = scanner.nextInt();
				switch (choice2) {
				case 1:
					String accountId = addAccount();
					if (accountId != null) {
						System.out.println("Account created, account Id is :" + accountId);
					} else {
						System.out.println(ErrorConstants.ACCOUNT_CREATION_ERROR);
					}
					break;
				case 2:
					System.out.println("Enter your option: " + "\n1. Update Name" + "\n2. Update Contact"
							+ "\n3. Update Address Details");
					choice3 = scanner.nextInt();
					switch (choice3) {
					case 1:
						boolean isUpdated = updateCustomerName();
						if (isUpdated) {
							System.out.println("Details Updated");
						} else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					case 2:
						isUpdated = updateCustomerContact();
						if (isUpdated) {
							System.out.println("Details Updated");
						} else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					case 3:
						isUpdated = updateCustomerAddress();
						if (isUpdated) {
							System.out.println("Details Updated");
						} else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					}
					break;
				case 3:
					boolean updated = deleteAccount();
					if (updated) {
						System.out.println("Account Deleted");
					} else {
						System.out.println(ErrorConstants.DELETE_ACCOUNT_ERROR);
					}
					break;
				case 4:
					System.out.println("Enter your option: " + "\n1. Update Passbook" + "\n2. Display Account Summary");
					int choice4 = scanner.nextInt();
					switch (choice4) {
					case 1: // update passbook
						break;
					case 2: // account Summary
						break;
					}
				case 5:
					System.out.println("Enter your option: " + "\n1. Credit Amount" + "\n2. Debit Account");
					int choice5 = scanner.nextInt();
					switch (choice5) {
					case 1:
						System.out
								.println("Enter your option: " + "\n1. Credit Using Slip" + "\n2. Credit Using Cheque");
						int choice6 = scanner.nextInt();
						switch (choice6) {
						case 1: // credit using slip

							int transIdCreditSlip = creditUsingSlip();
							if (transIdCreditSlip != 0) {
								System.out.println(
										"The Transaction is Successful.\nThe Transaction ID is :" + transIdCreditSlip);
							} else {
								System.out.println("Error occured while transaction");
							}

							break;
						case 2: // credit using cheque

							int transIdCreditCheque = creditUsingCheque();
							if (transIdCreditCheque != 0) {
								System.out.println("The Transaction is Successful.\nThe Transaction ID is :"
										+ transIdCreditCheque);
							} else {
								System.out.println("Error occured while transaction");
							}

							break;
						}
						break;
					case 2:
						System.out.println(
								"Enter your option: " + "\n1. Debit Using Slip" + "\n2. Debit Using Cheque");
						int choice7 = scanner.nextInt();
						switch (choice7) {
						case 1: // debit using slip

							int transIdDebitSlip = debitUsingSlip();
							if (transIdDebitSlip != 0) {
								System.out.println(
										"The Transaction is Successful.\nThe Transaction ID is :" + transIdDebitSlip);
							} else {
								System.out.println("Error occured while transaction");
							}

							break;
						case 2: // debit using cheque

							System.out.println();
							int transIdDebitCheque = debitUsingCheque();
							if (transIdDebitCheque != 0) {
								System.out.println(
										"The Transaction is Successful.\nThe Transaction ID is :" + transIdDebitCheque);
							} else {
								System.out.println("Error occured while transaction");
							}

							break;
						}
						break;
					}
					break;
				case 6:
					System.out.println("Enter your option: \n1. Create Loan request \n2. Disburse Loan Amount");
					int choice8 = scanner.nextInt();
					switch(choice8) {
					case 1:
						boolean requestSuccess = addLoanDetails();
						if(requestSuccess)
						{
							System.out.println("Loan request added successfully.");
						}
						break;
					case 2:
						loanDisbursal();
						break;
					}
					break;
					
				}
				while (true) {
					System.out.println("Do you want to perform another operation (y/n)?");
					String temp = br.readLine();
					if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("n")) {
						flag = temp;
						break;
					} else {
						System.out.println("Invalid Character Entered. Please enter again.");
					}

				}

			}
			System.out.println("Exiting..");
			break;

		case 2:
			System.out.println("Exiting..");
			break;
		}

	}

	public static boolean login(String username, String password) {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);

		boolean success = false;
		LoginService loginService = new LoginServiceImpl();
		try {
			success = loginService.validateEmail(login);
		} catch (PecuniaException | LoginException e) {
			System.out.println(e.getMessage());
		}
		return success;
	}

	public static String addAccount() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String accountId = null;
		System.out.println("Enter Customer Details:");
		Customer cust = new Customer();
		Address add = new Address();
		Account acc = new Account();
		System.out.println("Enter customer name: ");
		String custName = br.readLine();
		// validate
		cust.setName(custName);
		System.out.println("Enter customer aadhar: ");
		String aadhar = br.readLine();
		// validate
		cust.setAadhar(aadhar);
		System.out.println("Enter customer PAN: ");
		String pan = br.readLine();
		// validate
		cust.setPan(pan);
		System.out.println("Enter customer contact: ");
		String contact = br.readLine();
		// validate
		cust.setContact(contact);
		System.out.println("Enter customer Gender(M/F): ");
		String gender = br.readLine();
		// validate
		cust.setGender(gender);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println("Enter customer DOB: ");
		String dob = br.readLine();
		// validate
		cust.setDob(LocalDate.parse(dob, dateTimeFormatter));
		System.out.println("Enter Customer Address Details:");
		System.out.println("Enter Address Line 1: ");
		String addressLine1 = br.readLine();
		// validate
		add.setLine1(addressLine1);
		System.out.println("Enter Address Line 2: ");
		String addressLine2 = br.readLine();
		// validate
		add.setLine2(addressLine2);
		System.out.println("Enter Address City: ");
		String city = br.readLine();
		// validate
		add.setCity(city);
		System.out.println("Enter Address State: ");
		String state = br.readLine();
		// validate
		add.setState(state);
		System.out.println("Enter Address Country: ");
		String country = br.readLine();
		// validate
		add.setCountry(country);
		System.out.println("Enter Address Zipcode: ");
		String zipcode = br.readLine();
		// validate
		add.setZipcode(zipcode);

		System.out.println("Enter Account type: ");
		String accountType = br.readLine();
		acc.setAccountType(accountType);
		System.out.println("Enter Account Balance: ");
		Double balance = Double.parseDouble(br.readLine());
		acc.setBalance(balance);
		System.out.println("Enter BranchID: ");
		String branchId = br.readLine();
		acc.setBranchId(branchId);
		System.out.println("Enter interest: ");
		Double interest = Double.parseDouble(br.readLine());
		acc.setInterest(interest);

		acc.setStatus("ACTIVE");

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			accountId = ams.addAccount(cust, add, acc);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}

		return accountId;
	}

	public static boolean updateCustomerName() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		Account acc = new Account();
		Customer cust = new Customer();
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		acc.setId(accId);
		System.out.println("Enter the new Name: ");
		String name = br.readLine();
		// validate
		cust.setName(name);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerName(acc, cust);

		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean updateCustomerContact() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		Account acc = new Account();
		acc.setId(accId);
		System.out.println("Enter the new Contact: ");
		String contact = br.readLine();
		// validate
		Customer cust = new Customer();
		cust.setContact(contact);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerContact(acc, cust);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean updateCustomerAddress() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		Account acc = new Account();
		acc.setId(accId);
		Address add = new Address();
		System.out.println("Enter the new Address Details: ");
		System.out.println("Enter Address Line 1: ");
		String addressLine1 = br.readLine();
		// validate
		add.setLine1(addressLine1);
		System.out.println("Enter Address Line 2: ");
		String addressLine2 = br.readLine();
		// validate
		add.setLine1(addressLine2);
		System.out.println("Enter Address City: ");
		String city = br.readLine();
		// validate
		add.setCity(city);
		System.out.println("Enter Address State: ");
		String state = br.readLine();
		// validate
		add.setState(state);
		System.out.println("Enter Address Country: ");
		String country = br.readLine();
		// validate
		add.setCountry(country);
		System.out.println("Enter Address Zipcode: ");
		String zipcode = br.readLine();
		// validate
		add.setZipcode(zipcode);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerAddress(acc, add);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean deleteAccount() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		boolean updated = false;
		Account acc = new Account();
		acc.setId(accId);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.deleteAccount(acc);

		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static int creditUsingSlip() throws IOException {

		Transaction transaction = new Transaction();
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Account ID: ");
		String accId = br1.readLine();

		System.out.println("Enter Amount: ");
		double amt = Double.parseDouble(br1.readLine());

		transaction.setAmount(amt);
		transaction.setAccountId(accId);
		transaction.setId(Constants.NA);

		TransactionService transService = new TransactionServiceImpl();
		int transId = 0;
		try {
			transId = transService.creditUsingSlip(transaction);

		} catch (TransactionException e) {

			System.out.println(e.getMessage());
		} catch (PecuniaException e) {

			System.out.println(e.getMessage());
		}
		return transId;

	}

	public static int debitUsingSlip() throws IOException {

		Transaction transaction = new Transaction();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Account ID: ");
		String accId = br.readLine();

		System.out.println("Enter Amount: ");
		double amt = Double.parseDouble(br.readLine());

		transaction.setAmount(amt);
		transaction.setAccountId(accId);
		transaction.setId(Constants.NA);

		TransactionService transService = new TransactionServiceImpl();
		int transId = 0;
		try {
			transId = transService.debitUsingSlip(transaction);

		} catch (TransactionException e) {

			System.out.println(e.getMessage());
		} catch (PecuniaException e) {

			System.out.println(e.getMessage());
		}
		return transId;

	}

	public static int debitUsingCheque() throws IOException {

		Transaction debitChequeTransaction = new Transaction();

		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Account ID: ");
		String accIdDebitCheque = br1.readLine();

		System.out.println("Enter Amount: ");
		double amtDebitCheque = Double.parseDouble(br1.readLine());

		System.out.println("Enter Cheque Number: ");
		int chequeNumber = Integer.parseInt(br1.readLine());

		System.out.println("Enter Account Holder Name: ");
		String accholderDebitChequeName = br1.readLine();

		System.out.println("Enter IFSC Code: ");
		String chequeIfsc = br1.readLine();

		System.out.println("Enter Cheque Issue Date: ");
		String chequeIssueDate = br1.readLine();

		debitChequeTransaction.setAmount(amtDebitCheque);
		debitChequeTransaction.setAccountId(accIdDebitCheque);
		debitChequeTransaction.setId(Constants.NA);

		Cheque debitCheque = new Cheque();
		debitCheque.setAccountNo(accIdDebitCheque);
		debitCheque.setHolderName(accholderDebitChequeName);
		debitCheque.setIfsc(chequeIfsc);
		debitCheque.setIssueDate(LocalDate.parse(chequeIssueDate));
		debitCheque.setNum(chequeNumber);

		TransactionService transServiceDebitCheque = new TransactionServiceImpl();
		int transId = 0;
		try {
			transId = transServiceDebitCheque.debitUsingCheque(debitChequeTransaction, debitCheque);

		} catch (TransactionException e) {

			System.out.println(e.getMessage());
		} catch (PecuniaException e) {

			System.out.println(e.getMessage());
		}
		return transId;
	}

	public static int creditUsingCheque() throws IOException {

		Transaction creditChequeTransaction = new Transaction();

		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Account ID of Payee: ");
		String accPayeeIdCreditCheque = br1.readLine();

		System.out.println("Enter Account ID of Benificiary: ");
		String accBenificiaryIdCreditCheque = br1.readLine();

		System.out.println("Enter Amount: ");
		double amtCreditCheque = Double.parseDouble(br1.readLine());

		System.out.println("Enter Cheque Number: ");
		int chequeNumber = Integer.parseInt(br1.readLine());

		System.out.println("Enter Account Payee Name: ");
		String accPayeeCreditChequeName = br1.readLine();

		System.out.println("Enter Account Benificiary Name: ");
		String accBenificiaryCreditChequeName = br1.readLine();

		System.out.println("Enter Payee Bank Name: ");
		String payeeBankName = br1.readLine();
		
		System.out.println("Enter Payee IFSC Code: ");
		String chequeIfsc = br1.readLine();

		System.out.println("Enter Cheque Issue Date: ");
		String chequeIssueDate = br1.readLine();

		creditChequeTransaction.setAmount(amtCreditCheque);
		creditChequeTransaction.setAccountId(accBenificiaryIdCreditCheque);
		creditChequeTransaction.setTransTo(accBenificiaryIdCreditCheque);
		creditChequeTransaction.setTransFrom(accPayeeIdCreditCheque);


		Cheque creditCheque = new Cheque();
		creditCheque.setAccountNo(accPayeeIdCreditCheque);
		creditCheque.setHolderName(accPayeeCreditChequeName);
		creditCheque.setIfsc(chequeIfsc);
		creditCheque.setIssueDate(LocalDate.parse(chequeIssueDate));
		creditCheque.setNum(chequeNumber);
		creditCheque.setBankName(payeeBankName);

		TransactionService transServiceCreditCheque = new TransactionServiceImpl();
		int transChequeCreditId = 0;
		try {
			
			transChequeCreditId = transServiceCreditCheque.creditUsingCheque(creditChequeTransaction, creditCheque);

		} catch (TransactionException e) {

			System.out.println(e.getMessage());
		} catch (PecuniaException e) {

			System.out.println(e.getMessage());
		}
		return transChequeCreditId;
	}

	public static boolean addLoanDetails() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		LoanServiceImpl lsi = new LoanServiceImpl();
		
		boolean result= false;
		try {
				System.out.println("Enter account Id : ");
				String accId = br.readLine();
				System.out.println("Enter Loan Amount : ");
				double amount = scanner.nextDouble();
				System.out.println("Enter Rate of interest :");
				double roi = scanner.nextDouble();
				System.out.println("Enter Tenure:");
				int tenure = scanner.nextInt();
				System.out.println("Enter credit score : ");
				int creditScore = scanner.nextInt();
				System.out.println(
						"Select type of Loan :\n Type '1' for Personal Loan \n Type '2' for House Loan \n Type '3' for Vehicle Loan \n Type '4' for Jewel Loan");
				int input = Integer.parseInt(br.readLine());
				String type = null;
				if (input == 1) {
					type = Constants.LOAN_TYPE[0];
				} else if (input == 2) {
					type = Constants.LOAN_TYPE[1];
				} else if (input == 3) {
					type = Constants.LOAN_TYPE[2];
				} else if (input == 4) {
					type = Constants.LOAN_TYPE[3];
				}
				double emi = lsi.calculateEMI(amount, tenure, roi);
				Loan loan = new Loan();
				loan.setAmount(amount);
				loan.setCreditScore(creditScore);
				loan.setEmi(emi);
				loan.setLoanStatus("Pending");
				loan.setRoi(roi);
				loan.setTenure(tenure);
				loan.setType(type);
				loan.setAccountId(accId);
				LoanService loaserim = new LoanServiceImpl();
				result = loaserim.createLoanRequest(loan);

		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
			br.close();
		}
		return result;
	}

	public static void loanDisbursal() throws LoanDisbursalException
	{
		Scanner sc = new Scanner(System.in);
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		ArrayList<Loan> approvedLoanRequests = new ArrayList<Loan>();
		ArrayList<Loan> rejectedLoanRequests = new ArrayList<Loan>();
		ArrayList<LoanDisbursal> loanDisbursedData = new ArrayList<LoanDisbursal>();
		String update;
		LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
		
		try {
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
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
