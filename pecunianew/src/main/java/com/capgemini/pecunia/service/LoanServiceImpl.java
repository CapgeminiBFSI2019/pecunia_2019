package com.capgemini.pecunia.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.InvalidParameterException;

import com.capgemini.pecunia.Values;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.model.LoanRequest;

public class LoanServiceImpl {
	/*
	 * Method to calculate EMI
	 */
	public double calculateEMI(double amount, int tenure, double roi) {
		if (amount < 0 || tenure < 0 || roi < 0) {
			throw new InvalidParameterException("Amount, tenure and rate of interest cannot be negative.");
		}
		double p = amount;
		double r = roi / 1200;
		double a = Math.pow(1 + r, tenure);
		double emi = (p * r * a) / (a-1);
		return Math.round(emi);
	}
	
	//Read through Database instead of file 
	public boolean validateCustomerId(String customerId) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\Users\\risrai\\git\\capgemini_pecunia_2019\\Pecunia\\src\\main\\java\\com\\capgemini\\pecunia\\dao\\DbFiles\\Customer.csv"));
			String line;
			while ((line = br.readLine()) != null) {
				String arr[] = line.split(",");
				if (arr[0].equals(customerId)) {
					br.close();
					return true;
				}
			}
			br.close();
			return false;
		} catch (Exception e) {

			return false;
		}
	}
	public String createLoanRequest(Loan loan) {
		String customerId=loan.getCustomerId();
		String loanType=loan.getType();
		String loanStatus= loan.getLoanStatus();
		double amount=loan.getAmount();
		int tenure=loan.getTenure();
		double roi=loan.getRoi();
		int creditScore=loan.getCreditScore();

		try {
		if (!validateCustomerId(customerId)) {
			throw new InvalidParameterException();
		}
		if (customerId.equals(null) || loanType.equals(null) ||loanStatus.equals(null)) {
			return null;
		}
		if (loanType != "Home Loan" || loanType != "Vehicle Loan" || loanType != "Jewel Loan"
				|| loanType != "Personal Loan") {
			throw new InvalidParameterException();
		}
		if (amount < 0 || tenure < 0 || roi < 0) {
			throw new InvalidParameterException();
		}
		if (loanStatus != "Pending") {
			throw new InvalidParameterException();
		}
		if (creditScore <= 0) {
			throw new InvalidParameterException();
		}

		// Getting EMI using calculateEMI method for given values of loan amount,tenure
		// and rate of interest
		double emi = calculateEMI(amount, tenure, roi);

 		    //Getting loan request ID generated using Utility function	
			//String loanRequestId = Utility.getAlphaNumericString(20);


		// Creating object of loanRequest and passing values to it
		Loan loan = new Loan(customerId, amount, loanType, tenure,roi,
				loanStatus, emi, creditScore);

		// Writing loan applicant's loan Data to file
			String loanRequestData = loanreq.getLoanRequestData();
			File loancustomerFile = new File(Values.LOAN_REQUEST_CSV_FILE1);
			FileWriter fr = new FileWriter(loancustomerFile, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(loanRequestData);
			br.newLine(); 
			br.close();
			return loanRequestId;
		}catch (InvalidParameterException e) {
			throw new InvalidParameterException("Some input mismatch found.");
		} 
		catch (Exception e) {
			return null;
		}
	}	
}
