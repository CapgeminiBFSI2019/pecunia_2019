package com.capgemini.pecunia.pl;

import java.util.Scanner;

public class PecuniaMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice1 = 0, choice2 = 0, choice3 = 0;
		System.out.println("Enter your option: " + "1. Login" + "2. Exit");
		choice1 = scanner.nextInt();
		switch (choice1) {
		case 1:
			// validateLogin
			// if validated
			// Case true
			String flag = "y";
			while (flag == "y") {
				System.out.println("Enter your option: " + "1. Create Account" + "2. Update Account"
						+ "3. Delete Existing Account" + "4. Update Passbook/ Account Summary" + "5. Transaction");
				choice2 = scanner.nextInt();
				switch (choice2) {
				case 1: // Account creation function
					break;
				case 2:
					System.out.println("Enter your option: " + "1. Update Name" + "2. Update Contact"
							+ "3. Update Address Details");
					choice3 = scanner.nextInt();
					switch (choice3) {
					case 1: // call updateCustomerName
						break;
					case 2: // call updateCustomerContact
						break;
					case 3: // call updateCustomerAddress
						break;
					}
				case 3:
					// Delete Account
					break;
				case 4:
					System.out.println("Enter your option: " + "1. Update Passbook" + "2. Display Account Summary");
					int choice4 = scanner.nextInt();
					switch (choice4) {
					case 1: // update passbook
						break;
					case 2: // account Summary
						break;
					}
				case 5:
					System.out.println("Enter your option: " + "1. Credit Amount" + "2. Debit Account");
					int choice5 = scanner.nextInt();
					switch (choice5) {
					case 1:
						System.out.println("Enter your option: " + "1. Credit Using Slip" + "2. Credit Using Cheque");
						int choice6 = scanner.nextInt();
						switch (choice6) {
						case 1: // credit using slip
							break;
						case 2: // credit using cheque
							break;
						}
					case 2:
						System.out.println(
								"Enter your option: " + "1. Debit Using Withdrawl Form" + "2. Debit Using Cheque");
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

}
