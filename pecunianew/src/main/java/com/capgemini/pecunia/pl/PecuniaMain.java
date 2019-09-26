package com.capgemini.pecunia.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;
import com.capgemini.pecunia.util.Constants;

public class PecuniaMain {
	public static void main(String[] args) throws IOException {
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
			do
			{
				System.out.print("Enter username : ");
				String username = scanner.next();
				System.out.print("Enter password : ");
				String password = scanner.next();
				loginFlag = login(username,password);
				if(loginFlag)
				{
					System.out.println("Login successful");
					break;
				}
				else
				{
					System.out.println("Login Unsuccessful");
					System.out.print("Do you want to continue Y / N :-");
					String choice = scanner.next();
					if(choice.equalsIgnoreCase("n"))
					{
						break;
					}
					else if(!choice.equalsIgnoreCase("y"))
					{
						System.out.println("Invalid choice");
					}
				}
			}
			while(!loginFlag);
			String flag = "y";
			while (flag == "y") {
				System.out.println("Enter your option: " + "\n1. Create Account" + "\n2. Update Account"
						+ "\n3. Delete Existing Account" + "\n4. Update Passbook/ Account Summary" + "\n5. Transaction");
				choice2 = scanner.nextInt();
				switch (choice2) {
				case 1:
					String accountId = addAccount();
					if(accountId != null)
					{
						System.out.println(accountId);
					}
					else
					{
						System.out.println(ErrorConstants.ACCOUNT_CREATION_ERROR);
					}
					break;
				case 2:
					System.out.println("Enter your option: " + "\n1. Update Name" + "\n2. Update Contact"
							+ "\n3. Update Address Details");
					choice3 = scanner.nextInt();
					switch (choice3) {
					case 1: 
						boolean updated  =updateCustomerName();
						if(updated) {
							System.out.println("Details Updated");
						}
						else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					case 2: 
						updated  =updateCustomerContact();
						if(updated) {
							System.out.println("Details Updated");
						}
						else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					case 3: 
						updated  =updateCustomerAddress();
						if(updated) {
							System.out.println("Details Updated");
						}
						else {
							System.out.println(ErrorConstants.UPDATE_ACCOUNT_ERROR);
						}
						break;
					}
					break;
				case 3:
					boolean updated = deleteAccount();
					if(updated) {
						System.out.println("Account Deleted");
					}
					else {
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
						System.out.println("Enter your option: " + "\n1. Credit Using Slip" + "\n2. Credit Using Cheque");
						int choice6 = scanner.nextInt();
						switch (choice6) {
						case 1: // credit using slip
							break;
						case 2: // credit using cheque
							break;
						}
					case 2:
						System.out.println(
								"Enter your option: " + "\n1. Debit Using Withdrawl Form" + "\n2. Debit Using Cheque");
						int choice7 = scanner.nextInt();
						switch (choice7) {
						case 1: // debit using slip
							break;
						case 2: // debit using cheque
							break;
						}
					}
				}
				while (true) {
					System.out.println("Do you want to perform another operation (y/n)?");
					String temp = scanner.nextLine();
					if (temp == "y" || temp == "n") {
						flag = temp;
						break;
					} else {
						System.out.println("Invalid Character Entered. Please enter again.");
					}
					if (flag == "y" || flag == "n") {
						break;
					}
				}

			}
			break;

		case 2:
			System.out.println("Exiting..");
			break;
		}

	}
	
	public static boolean login(String username,String password)
	{
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		
		boolean success = false;
		LoginService loginService = new LoginServiceImpl();
		try {
			success = loginService.validateEmail(login);
		} catch (MyException | LoginException e) {
			System.out.println(e.getMessage());
		}
		return success;
	}
	
	public static String addAccount() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String accountId = null;
		System.out.println("Enter Customer Details:");
		Customer cust = new Customer();
		Address add = new Address();
		Account acc = new Account();
		System.out.println("Enter customer name: ");
		String custName = br.readLine();
		//validate
		cust.setName(custName);
		System.out.println("Enter customer aadhar: ");
		String aadhar = br.readLine();
		//validate
		cust.setAadhar(aadhar);
		System.out.println("Enter customer PAN: ");
		String pan = br.readLine();
		//validate
		cust.setPan(pan);
		System.out.println("Enter customer contact: ");
		String contact = br.readLine();
		//validate
		cust.setContact(contact);
		System.out.println("Enter customer Gender(M/F): ");
		String gender = br.readLine();
		//validate
		cust.setGender(gender);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println("Enter customer DOB: ");
		String dob = br.readLine();
		//validate
		cust.setDob(LocalDate.parse(dob, dateTimeFormatter));
		System.out.println("Enter Customer Address Details:");
		System.out.println("Enter Address Line 1: ");
		String addressLine1 = br.readLine();
		//validate
		add.setLine1(addressLine1);
		System.out.println("Enter Address Line 2: ");
		String addressLine2 = br.readLine();
		//validate
		add.setLine2(addressLine2);
		System.out.println("Enter Address City: ");
		String city = br.readLine();
		//validate
		add.setCity(city);
		System.out.println("Enter Address State: ");
		String state = br.readLine();
		//validate
		add.setState(state);
		System.out.println("Enter Address Country: ");
		String country = br.readLine();
		//validate
		add.setCountry(country);
		System.out.println("Enter Address Zipcode: ");
		String zipcode = br.readLine();
		//validate
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
		} catch (MyException | AccountException e) {
			System.out.println(e.getMessage());
		}
		
		return accountId;
	}

	
	public static boolean updateCustomerName() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated=false;
		Account acc = new Account();
		Customer cust = new Customer();
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		acc.setId(accId);
		System.out.println("Enter the new Name: ");
		String name = br.readLine();
		//validate
		cust.setName(name);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerName(acc, cust);
			
		} catch (MyException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}
	
	public static boolean updateCustomerContact() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated=false;
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		Account acc = new Account();
		acc.setId(accId);
		System.out.println("Enter the new Contact: ");
		String contact = br.readLine();
		//validate
		Customer cust = new Customer();
		cust.setContact(contact);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerContact(acc, cust);
		} catch (MyException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}
	
	public static boolean updateCustomerAddress() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean updated=false;
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		Account acc = new Account();
		acc.setId(accId);
		Address add= new Address();
		System.out.println("Enter the new Address Details: ");
		System.out.println("Enter Address Line 1: ");
		String addressLine1 = br.readLine();
		//validate
		add.setLine1(addressLine1);
		System.out.println("Enter Address Line 2: ");
		String addressLine2 = br.readLine();
		//validate
		add.setLine1(addressLine2);
		System.out.println("Enter Address City: ");
		String city = br.readLine();
		//validate
		add.setCity(city);
		System.out.println("Enter Address State: ");
		String state = br.readLine();
		//validate
		add.setState(state);
		System.out.println("Enter Address Country: ");
		String country = br.readLine();
		//validate
		add.setCountry(country);
		System.out.println("Enter Address Zipcode: ");
		String zipcode = br.readLine();
		//validate
		add.setZipcode(zipcode);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.updateCustomerAddress(acc, add);
		} catch (MyException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}
	
	
	public static boolean deleteAccount() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Account Id: ");
		String accId = br.readLine();
		boolean updated=false;
		Account acc = new Account();
		acc.setId(accId);
		AccountManagementService ams = new AccountManagementServiceImpl();
		try {
			updated = ams.deleteAccount(acc);
			
		} catch (MyException | AccountException e) {
			System.out.println(e.getMessage());
		}
		return updated;
	}
}
