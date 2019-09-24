package com.capgemini.pecunia.service;

 

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidParameterException;

import com.capgemini.pecunia.dto.Loan;

 
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
    public boolean validateAccountId(String string) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "C:\\Users\\risrai\\git\\capgemini_pecunia_2019\\Pecunia\\src\\main\\java\\com\\capgemini\\pecunia\\dao\\DbFiles\\Customer.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                if (arr[0].equals(string)) {
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;
        } catch (Exception e) {
	
	

        }
		return false;
    }
 
	public String createLoanRequest(Loan loan) {
		try {
		if (!validateAccountId(loan.getAccountId())) {
			throw new InvalidParameterException();
		}
		if (loan.getType().equals(null) ||loan.getLoanStatus().equals(null)) {
			return null;
		}
		if (loan.getType() != "Home Loan" || loan.getType() != "Vehicle Loan" || loan.getType() != "Jewel Loan"
				|| loan.getType() != "Personal Loan") {
			throw new InvalidParameterException();
		}
		if (loan.getAmount() < 0 || loan.getTenure() < 0 || loan.getRoi() < 0) {
			throw new InvalidParameterException();
		}
		if (loan.getLoanStatus() != "Pending") {
			throw new InvalidParameterException();
		}
		if (loan.getCreditScore() <= 0) {
			throw new InvalidParameterException();
		}
		}catch (InvalidParameterException e) {
			throw new InvalidParameterException("Some input mismatch found.");
		} 
		catch (Exception e) {
			return null;
		}
		// Getting EMI using calculateEMI method for given values of loan amount,tenure
		// and rate of interest
		double emi = calculateEMI(loan.getAmount(), loan.getTenure(), loan.getRoi());
		return null;
	}
}


