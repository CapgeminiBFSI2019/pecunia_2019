package com.capgemini.pecunia.pl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.hibernate.dao.LoanDisbursalDAOImplHibernate;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.hibernate.dao.AccountManagementDAO;
import com.capgemini.pecunia.hibernate.dao.AccountManagementDAOImpl;
import com.capgemini.pecunia.hibernate.dao.TransactionDAOImpl;

public class PecuniaMain {
	

	public static void main(String[] args) throws PecuniaException, TransactionException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImplHibernate l = new LoanDisbursalDAOImplHibernate();
		l.retrieveLoanList();
		
			
	

	
		
//		TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
//		transactionDAOImpl.getBalance(null);
//		
//		AccountManagementDAO accDAO = new AccountManagementDAOImpl();
//		Account account = new Account();
//		account.setId("100102000006");
//		Customer customer = new Customer();
//		customer.setName("Aditiii");
//		try {
//			accDAO.updateCustomerName(account, customer);
//		} catch (AccountException e) {
//			System.out.println(e.getMessage());
//		}
		
		try {
			TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
			Account account2 = new Account();
			account2.setId("100102000006");
			account2.setBalance(10000.0);
			boolean balanceUpdated = transactionDAOImpl.updateBalance(account2);
			System.out.println(balanceUpdated);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		

	}
/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice1 = 0, choice2 = 0, choice3 = 0;
		System.out.println("Enter your option: " + "\n1. Login" + "\n2. Exit");

		choice1 = Integer.parseInt(br.readLine());
		switch (choice1) {
		case 1:
			boolean loginFlag = false;
			do {
				System.out.print("Enter username : ");
				String username = br.readLine();
				System.out.print("Enter password : ");
				String password = br.readLine();
				loginFlag = login(username, password);
				if (loginFlag) {
					System.out.println("Login successful");
					break;
				} else {
					System.out.println("Login Unsuccessful");
					System.out.print("Do you want to continue Y / N :-");
					String choice = br.readLine();
					if ("n".equalsIgnoreCase(choice)) {
						break;
					} else if (!"y".equalsIgnoreCase(choice)) {
						System.out.println("Invalid choice");
					}
				}
			} while (!loginFlag);
			String option = "y";
			while ("y".equalsIgnoreCase(option)) {
				System.out.println("Enter your option: " + "\n1. Create Account" + "\n2. Update Account"
						+ "\n3. Delete Existing Account" + "\n4. Update Passbook/ Account Summary" + "\n5. Transaction"
						+ "\n6. Loan Management");
				choice2 = Integer.parseInt(br.readLine());
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
					choice3 = Integer.parseInt(br.readLine());
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
					int choice4 = Integer.parseInt(br.readLine());
					switch (choice4) {
					case 1: // update passbook
						updatePassbook();
						break;
					case 2: // account Summary
						accountSummary();
						break;
					}
				case 5:
					System.out.println("Enter your option: " + "\n1. Credit Amount" + "\n2. Debit Account");
					int choice5 = Integer.parseInt(br.readLine());
					switch (choice5) {
					case 1:
						System.out
								.println("Enter your option: " + "\n1. Credit Using Slip" + "\n2. Credit Using Cheque");
						int choice6 = Integer.parseInt(br.readLine());
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
						System.out.println("Enter your option: " + "\n1. Debit Using Slip" + "\n2. Debit Using Cheque");
						int choice7 = Integer.parseInt(br.readLine());
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

					int choice8 = Integer.parseInt(br.readLine());
					switch (choice8) {
					case 1:
						boolean requestSuccess = addLoanDetails();
						if (requestSuccess) {
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
					if ("y".equalsIgnoreCase(temp) || "n".equalsIgnoreCase(temp)) {
						option = temp;
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
		boolean isValid = true;
		String aadhar = null;
		String accountType=null;
		String pan=null; String contact=null; 
		String addressLine1 = null; String addressLine2 = null;
		String city=null; String state=null;
		String country=null; String zipcode=null;
		String gender=null; double balance=0.0;
		double interest=0.0;
		String branchId=null;
		String dob=null;
		System.out.println("Enter Customer Details:");
		Customer customer = new Customer();
		Address address = new Address();
		Account account = new Account();
		String customerName = null;
		do {
			System.out.println("Enter customer name: ");
			customerName = br.readLine();
			if (AccountInputValidator.checkIfAlphaNumeric(customerName) == true
					|| AccountInputValidator.checkIfDigit(customerName) == true
					|| AccountInputValidator.checkIfSpecialCharacter(customerName) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}

		} while (!isValid);
		customer.setName(customerName);
		isValid=true;
		do {
			System.out.println("Enter customer aadhar: ");
			aadhar = br.readLine();
			if (AccountInputValidator.checkIfAlphaNumeric(aadhar) == true
					|| AccountInputValidator.checkLength(12, aadhar) == false
					|| AccountInputValidator.checkIfSpecialCharacter(aadhar) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}

		} while (!isValid);
		customer.setAadhar(aadhar);
		isValid=true;
		do {
			System.out.println("Enter customer PAN: ");
			pan = br.readLine();
			if (AccountInputValidator.checkLength(10, pan) == false || AccountInputValidator.checkIfSpecialCharacter(pan) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		customer.setPan(pan);
		isValid=true;
		do {
			System.out.println("Enter customer contact: ");
			contact = br.readLine();
			if (AccountInputValidator.checkLength(10, contact) == false || AccountInputValidator.checkIfSpecialCharacter(contact) == true || AccountInputValidator.checkIfAlphaNumeric(contact) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		customer.setContact(contact);
		
		isValid=true;
		do {
			System.out.println("Enter customer Gender(M/F): ");
			gender = br.readLine();
			if (AccountInputValidator.checkLength(1, gender) == false || AccountInputValidator.checkIfSpecialCharacter(gender) == true || AccountInputValidator.checkIfAlphaNumeric(gender) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		customer.setGender(gender);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		isValid=true;
		do {
			System.out.println("Enter customer DOB: ");
			dob = br.readLine();
			if (AccountInputValidator.dateValidator(dob) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		customer.setDob(LocalDate.parse(dob, dateTimeFormatter));
		System.out.println("Enter Customer Address Details:");
		
		isValid=true;
		do {
			System.out.println("Enter Address Line 1: ");
			addressLine1 = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(addressLine1) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setLine1(addressLine1);		
		isValid=true;
		do {
			System.out.println("Enter Address Line 2: ");
			addressLine2 = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(addressLine2) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setLine2(addressLine2);
		isValid=true;
		do {
			System.out.println("Enter Address City: ");
			city = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(city) == true || AccountInputValidator.checkIfAlphaNumeric(city) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setCity(city);

		isValid=true;
		do {
			System.out.println("Enter Address State: ");
			state = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(state) == true || AccountInputValidator.checkIfAlphaNumeric(state) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setState(state);
		
		isValid=true;
		do {
			System.out.println("Enter Address Country: ");
			country = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(country) == true || AccountInputValidator.checkIfAlphaNumeric(country) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setCountry(country);
		
		isValid=true;
		do {
			System.out.println("Enter Address Zipcode: ");
			zipcode = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(zipcode) == true || AccountInputValidator.checkIfAlphaNumeric(zipcode) == true || AccountInputValidator.checkLength(6, zipcode) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setZipcode(zipcode);

		
		isValid=true;
		do {
			System.out.println("Enter Account type: ");
			accountType = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accountType) == true || AccountInputValidator.checkIfAlphaNumeric(accountType) == true || accountType!=Constants.SAVINGS || accountType!=Constants.CURRENT || accountType!=Constants.FD || accountType!=Constants.LOAN) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setAccountType(accountType);
		
		isValid=true;
		do {
			System.out.println("Enter Account Balance: ");
			balance = Double.parseDouble(br.readLine());
			if (balance<0) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setBalance(balance);
		
		isValid=true;
		do {
			System.out.println("Enter BranchID: ");
			branchId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(branchId) == true || AccountInputValidator.checkIfAlphaNumeric(branchId) == true || AccountInputValidator.checkLength(4, branchId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setBranchId(branchId);
		
		isValid=true;
		do {
			System.out.println("Enter interest: ");
			interest = Double.parseDouble(br.readLine());
			if (interest<0) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setInterest(interest);
		
		account.setStatus("ACTIVE");

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			accountId = ams.addAccount(customer, address, account);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}

		return accountId;
	}

	public static boolean updateCustomerName() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		String accId=null;
		String name=null;
		Account account = new Account();
		Customer customer = new Customer();
		
		boolean isValid=true;
		do {
			System.out.println("Enter the Account Id: ");
			accId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true || AccountInputValidator.checkIfAlphaNumeric(accId) == true || AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setId(accId);
		isValid=true;
		do {
			System.out.println("Enter the new Name: ");
			name = br.readLine();
			if (AccountInputValidator.checkIfAlphaNumeric(name) == true
					|| AccountInputValidator.checkIfDigit(name) == true
					|| AccountInputValidator.checkIfSpecialCharacter(name) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}

		} while (!isValid);
		customer.setName(name);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerName(account, customer);

		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean updateCustomerContact() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		boolean isValid=true;
		String accId=null;
		String contact=null;
		do {
			System.out.println("Enter the Account Id: ");
			accId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true || AccountInputValidator.checkIfAlphaNumeric(accId) == true || AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		Account account = new Account();
		account.setId(accId);
		
		isValid=true;
		do {
			System.out.println("Enter the new Contact: ");
			contact = br.readLine();
			if (AccountInputValidator.checkLength(10, contact) == false || AccountInputValidator.checkIfSpecialCharacter(contact) == true || AccountInputValidator.checkIfAlphaNumeric(contact) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		Customer customer = new Customer();
		customer.setContact(contact);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerContact(account, customer);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean updateCustomerAddress() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated = false;
		boolean isValid=true;
		String accId=null;
		String addressLine1 = null; String addressLine2 = null;
		String city=null; String state=null;
		String country=null; String zipcode=null;
		Account account = new Account();
		do {
			System.out.println("Enter the Account Id: ");
			accId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true || AccountInputValidator.checkIfAlphaNumeric(accId) == true || AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setId(accId);
		Address address = new Address();
		System.out.println("Enter the new Address Details: ");
		isValid=true;
		do {
			System.out.println("Enter Address Line 1: ");
			addressLine1 = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(addressLine1) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setLine1(addressLine1);		
		isValid=true;
		do {
			System.out.println("Enter Address Line 2: ");
			addressLine2 = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(addressLine2) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setLine2(addressLine2);
		isValid=true;
		do {
			System.out.println("Enter Address City: ");
			city = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(city) == true || AccountInputValidator.checkIfAlphaNumeric(city) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setCity(city);

		isValid=true;
		do {
			System.out.println("Enter Address State: ");
			state = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(state) == true || AccountInputValidator.checkIfAlphaNumeric(state) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setState(state);
		
		isValid=true;
		do {
			System.out.println("Enter Address Country: ");
			country = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(country) == true || AccountInputValidator.checkIfAlphaNumeric(country) == true) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setCountry(country);
		
		isValid=true;
		do {
			System.out.println("Enter Address Zipcode: ");
			zipcode = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(zipcode) == true || AccountInputValidator.checkIfAlphaNumeric(zipcode) == true || AccountInputValidator.checkLength(6, zipcode) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		address.setZipcode(zipcode);

		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerAddress(account, address);
		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static boolean deleteAccount() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String accId=null;
		
		boolean updated = false;
		Account account = new Account();
		boolean isValid=true;
		do {
			System.out.println("Enter the Account Id: ");
			accId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true || AccountInputValidator.checkIfAlphaNumeric(accId) == true || AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			}
		} while (isValid==false);
		account.setId(accId);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.deleteAccount(account);

		} catch (PecuniaException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}

	public static int creditUsingSlip() throws IOException {

		Transaction transaction = new Transaction();
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		boolean isValid = true;
		String accId = null;
		do {
			System.out.println("Enter the Account Id: ");
			accId = br1.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true
					|| AccountInputValidator.checkIfAlphaNumeric(accId) == true
					|| AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			} else {
				isValid = true;
			}
		} while (!isValid);

		isValid = false;
		double amt = 0.0;
		TransactionInputValidator transValidator = new TransactionInputValidator();

		System.out.println("Enter Amount: ");
		amt = Double.parseDouble(br1.readLine());

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

		boolean isValid = true;
		String accId = null;
		do {
			System.out.println("Enter the Account Id: ");
			accId = br.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accId) == true
					|| AccountInputValidator.checkIfAlphaNumeric(accId) == true
					|| AccountInputValidator.checkLength(12, accId) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			} else {
				isValid = true;
			}
		} while (!isValid);

		isValid = true;
		double amt = 0.0;
		TransactionInputValidator transValidator = new TransactionInputValidator();

			boolean typeMatch = false;
			do {
				System.out.println("Enter Amount: ");
				try {
					amt = Double.parseDouble(br.readLine());
					typeMatch = true;
				} catch (NumberFormatException e) {
					System.out.println("Wrong type entered");
				}
			} while (!typeMatch);


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

		boolean isValid = true;
		String accIdDebitCheque = null;
		do {
			System.out.println("Enter the Account Id: ");
			accIdDebitCheque = br1.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accIdDebitCheque) == true
					|| AccountInputValidator.checkIfAlphaNumeric(accIdDebitCheque) == true
					|| AccountInputValidator.checkLength(12, accIdDebitCheque) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			} else {
				isValid = true;
			}
		} while (!isValid);

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

		boolean isValid = true;
		String accPayeeIdCreditCheque = null;
		do {
			System.out.println("Enter Account ID of Payee: ");
			accPayeeIdCreditCheque = br1.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accPayeeIdCreditCheque) == true
					|| AccountInputValidator.checkIfAlphaNumeric(accPayeeIdCreditCheque) == true
					|| AccountInputValidator.checkLength(12, accPayeeIdCreditCheque) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			} else {
				isValid = true;
			}
		} while (!isValid);

		isValid = true;
		String accBenificiaryIdCreditCheque = null;
		do {
			System.out.println("Enter Account ID of Benificiary: ");
			accBenificiaryIdCreditCheque = br1.readLine();
			if (AccountInputValidator.checkIfSpecialCharacter(accBenificiaryIdCreditCheque) == true
					|| AccountInputValidator.checkIfAlphaNumeric(accBenificiaryIdCreditCheque) == true
					|| AccountInputValidator.checkLength(12, accBenificiaryIdCreditCheque) == false) {
				System.out.println("Invalid. Enter again.");
				isValid = false;
			} else {
				isValid = true;
			}
		} while (!isValid);


		isValid = false;
		double amtCreditCheque = 0.0;
		TransactionInputValidator transValidator = new TransactionInputValidator();
		do {
		System.out.println("Enter Amount: ");
		amtCreditCheque = Double.parseDouble(br1.readLine());
		System.out.println("Enter Cheque Number: ");
		int chequeNumber = Integer.parseInt(br1.readLine());
			if (!transValidator.transactionAmountisValid(amtCreditCheque)) {
				System.out.println("Invalid. Enter again.");

			} else {
				isValid = true;
			}
		} while (!isValid);

		isValid = false;
		int chequeNumber = 0;
		do {
			boolean typeMatch = false;
			do {
				System.out.println("Enter Cheque Number: ");
				try {
					chequeNumber = Integer.parseInt(br1.readLine());
					typeMatch = true;
				} catch (NumberFormatException e) {
					System.out.println("Wrong type entered");
				}
			} while (!typeMatch);
			if (!transValidator.chequeNumberisValid(chequeNumber)) {
				System.out.println("Invalid. Enter again.");
			} else {
				isValid = true;
			}
		} while (!isValid);

		isValid = false;
		String accPayeeCreditChequeName = null;
		do {
			System.out.println("Enter Account Holder Name: ");
			accPayeeCreditChequeName = br1.readLine();
			if (!transValidator.validateAccountName(accPayeeCreditChequeName)) {
				System.out.println("Invalid. Enter again.");
			} else {
				isValid = true;
			}
		} while (!isValid);

		System.out.println("Enter Account Payee Name: ");
		accPayeeCreditChequeName = br1.readLine();


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

	public static boolean addLoanDetails() throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean result = false;
		System.out.println("Enter account Id : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String accId = br.readLine();
		LoanService loanServiceImpl = new LoanServiceImpl();
		double amount = 0.0, roi = 0.0;
		int tenure = 0, creditScore = 0;
		String type = null;
		try {
			try {
				do {
					System.out.println("Enter Loan Amount : ");
					amount = scanner.nextDouble();
					if (amount <= 0) {
						System.out.println("Amount cannot be negative or zero.");
					}
				} while (amount <= 0);
			} catch (NumberFormatException e) {
				System.out.println("Please enter amount in numeric format.");
			}

			try {
				do {
					System.out.println("Enter Rate of interest :");
					roi = scanner.nextDouble();
					if (roi <= 0) {
						System.out.println("Rate of interest cannot be negative or zero.");
					}
				} while (roi <= 0);
			} catch (NumberFormatException e) {
				System.out.println("Please enter rate of interest in numeric format.");
			}

			try {
				do {
					System.out.println("Enter Tenure:");
					tenure = scanner.nextInt();
					if (tenure <= 0) {
						System.out.println("Tenure cannot be negative or zero.");
					}
				} while (tenure <= 0);
			} catch (NumberFormatException e) {
				System.out.println("Please enter tenure in integer format.");
			}

			try {
				do {
					System.out.println("Enter credit score : ");
					creditScore = scanner.nextInt();
					if (creditScore <= 0) {
						System.out.println("Credit Score cannot be negative or zero.");
					}
				} while (creditScore <= 0);
			} catch (NumberFormatException e) {
				System.out.println("Please enter credit score in integer format.");
			}

			boolean isValidLoanType = false;

			while (!isValidLoanType) {
				try {
					System.out.println(
							"Select type of Loan :\n Type '1' for Personal Loan \n Type '2' for House Loan \n Type '3' for Vehicle Loan \n Type '4' for Jewel Loan");
					int input = Integer.parseInt(br.readLine());
					if (input == 1) {
						type = Constants.LOAN_TYPE[0];
						isValidLoanType = true;
					} else if (input == 2) {
						type = Constants.LOAN_TYPE[1];
						isValidLoanType = true;
					} else if (input == 3) {
						type = Constants.LOAN_TYPE[2];
						isValidLoanType = true;
					} else if (input == 4) {
						type = Constants.LOAN_TYPE[3];
						isValidLoanType = true;
					}
					if (!isValidLoanType) {
						System.out.println("Please select valid loan type.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter correct option for loan type.");
				}
			}

			double emi = LoanServiceImpl.calculateEMI(amount, tenure, roi);
			Loan loan = new Loan();
			loan.setAmount(amount);
			loan.setCreditScore(creditScore);
			loan.setEmi(emi);
			loan.setLoanStatus(Constants.LOAN_REQUEST_STATUS[0]);
			loan.setRoi(roi);
			loan.setTenure(tenure);
			loan.setType(type);
			loan.setAccountId(accId);
			result = loanServiceImpl.createLoanRequest(loan);

		} catch (LoanException e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
			br.close();
		}
		return result;
	}

	public static void loanDisbursal() throws LoanDisbursalException {

		 

        ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
        ArrayList<Loan> approvedLoanRequests = new ArrayList<Loan>();
        ArrayList<Loan> rejectedLoanRequests = new ArrayList<Loan>();
        ArrayList<LoanDisbursal> loanDisbursedData = new ArrayList<LoanDisbursal>();
        String update;
        LoanDisbursalService loanDisbursalService = new LoanDisbursalServiceImpl();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(
                        "press" + "\n" + "1. to retrieve loan requests" + "\n" + "2. to accept/reject loan requests"
                                + "\n" + "3. to retrieve the data from loan disbursed database" + "\n"
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

 

                        approvedLoanRequests = loanDisbursalService.approveLoan();

 

                        System.out.println("Approved loan requests");
                        System.out.println(approvedLoanRequests);

 

                        System.out.println("Rejected loan requests");
                        rejectedLoanRequests = loanDisbursalService.rejectedLoanRequests();
                        System.out.println(rejectedLoanRequests);

 

                        System.out.println("Approved loan requests");
                        System.out.println(approvedLoanRequests);

 

                        System.out.println("Rejected loan requests");
                        rejectedLoanRequests = loanDisbursalService.rejectedLoanRequests();
                        System.out.println(rejectedLoanRequests);

 

                        if (approvedLoanRequests.size() == 0 && rejectedLoanRequests.size() == 0)
                            System.out.println("No loan requests");
                        update = loanDisbursalService.updateLoanStatus(rejectedLoanRequests, approvedLoanRequests);
                        
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
                        String status = loanDisbursalService.updateExistingBalance(approvedLoanRequests);
                        if(Constants.STATUS_CHECK[0].equals(status)) {
                            System.out.println("Amount has been deducted");
                        }
                        else {
                            System.out.println("Amount has been deducted");
                        }
                    } catch (PecuniaException | TransactionException e) {
                        throw new LoanDisbursalException(e.getMessage());
                    }

 

                }
                
                else if (choice == 5) {

                	 
                  System.out.println("Enter the Account Number"); 
                  String accountId = sc.next();
                  String loans =  loanDisbursalService.numberOfLoanAccounts(approvedLoanRequests, accountId);
                  System.out.println(loans); 

                }
                
                else if (choice == 6) {

               	 
                    System.out.println("Enter the Account Number"); 
                    String accountId = sc.next();
                    double loans =  0; //loanDisbursalService.calculateEMIToBePaid(approvedLoanRequests, accountId);
                    System.out.println(loans); 

                  }

 

                else if (choice == 0) {

 

                    System.exit(1);

 

                }

 

                else {
                    System.out.println("INVALID CHOICE");
                }

 

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
			sc.close();
		}

 

    }

	public static void updatePassbook()
	{
		PassbookMaintenanceService PassbookService = new PassbookMaintenanceServiceImpl();
		List<Transaction> updatePassbook = new ArrayList<Transaction>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter accountId:");
		String accountId = scanner.nextLine();
		try {
			updatePassbook = PassbookService.updatePassbook(accountId);

			if (updatePassbook.size() < 1) {
				System.out.println("no trans");
			}

			else {
				for (int index = 0; index < updatePassbook.size(); index++) {
					System.out.print(updatePassbook.get(index).getId() + "\t");
					System.out.print(updatePassbook.get(index).getTransDate() + "\t");
					System.out.print(updatePassbook.get(index).getAmount() + "\t");
					System.out.print(updatePassbook.get(index).getTransFrom() + "\t");
					System.out.print(updatePassbook.get(index).getTransTo() + "\t");
					System.out.print(updatePassbook.get(index).getType() + "\t");
					System.out.print(updatePassbook.get(index).getOption() + "\t");
					if (updatePassbook.get(index).getOption().equalsIgnoreCase("cheque")) {
						System.out.print(updatePassbook.get(index).getChequeId() + "\t");

					} else {
						System.out.print("-\t");
					}
					System.out.print(updatePassbook.get(index).getClosingBalance() + "\t");
					System.out.println();
				}
			}
		} catch (PecuniaException | PassbookException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			scanner.close();
		}
	}

	public static void accountSummary()
	{
		Scanner scanner = new Scanner(System.in);
		
		PassbookMaintenanceService accountSummaryService = new PassbookMaintenanceServiceImpl();

		System.out.println("Enter accountId:");
		String accountId1 = scanner.nextLine();

		System.out.println("Enter start date:");
		String sdate1 = scanner.nextLine();

		System.out.println("Enter end date:");
		String sdate2 = scanner.nextLine();

		LocalDate date1 = LocalDate.parse(sdate1);
		LocalDate date2 = LocalDate.parse(sdate2);

		List<Transaction> accountSummary = new ArrayList<Transaction>();
		try {
			accountSummary = accountSummaryService.accountSummary(accountId1, date1, date2);
				for (int index = 0; index < accountSummary.size(); index++) {
					System.out.print(accountSummary.get(index).getId() + "\t");
					System.out.print(accountSummary.get(index).getTransDate() + "\t");
					System.out.print(accountSummary.get(index).getAmount() + "\t");
					System.out.print(accountSummary.get(index).getTransFrom() + "\t");
					System.out.print(accountSummary.get(index).getTransTo() + "\t");
					System.out.print(accountSummary.get(index).getType() + "\t");
					System.out.print(accountSummary.get(index).getOption() + "\t");
					if (accountSummary.get(index).getOption().equalsIgnoreCase("cheque")) {
						System.out.print(accountSummary.get(index).getChequeId() + "\t");

					} else {
						System.out.print("-\t");
					}
					System.out.print(accountSummary.get(index).getChequeId() + "\t");
					System.out.print(accountSummary.get(index).getClosingBalance() + "\t");
					System.out.println();
				}
			
		} catch (PecuniaException | PassbookException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			scanner.close();
		}
		
	}
	*/

}