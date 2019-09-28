package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dao.LoanDAO;
import com.capgemini.pecunia.dao.LoanDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.PecuniaException;

public class LoanServiceImpl implements LoanService {

	/*******************************************************************************************************
	 * -Function Name : calculateEMI(double amount, int tenure, double roi)
	 * -Input Parameters : double amount, int tenure, double roi 
	 * -Return Type : double 
	 * -Author : Rishabh Rai
	 * -Creation Date : 24/09/2019
	 * -Description : Takes the Amount,tenure and Rate of Interest as parameter and returns emi for the loan
	 ********************************************************************************************************/

	public double calculateEMI(double amount, int tenure, double roi) {
		double p = amount;
		double r = roi / 1200;
		double a = Math.pow(1 + r, tenure);
		double emi = (p * r * a) / (a - 1);
		return Math.round(emi);
	}

	/*******************************************************************************************************
	 * -Function Name :createLoanRequest(Loan loan) 
	 * -Input Parameters : Loan loan
	 * -Return Type : boolean 
	 * -Author : Rishabh Rai 
	 * -Creation Date : 24/09/2019
	 * -@Throws LoanException  
	 * -Description : Create entry for loan Request
	 ********************************************************************************************************/

	public boolean createLoanRequest(Loan loan) throws LoanException {
		boolean isRequestSuccess = false;
		LoanDAO ld = new LoanDAOImpl();
		try {
			isRequestSuccess= ld.addLoanDetails(loan);

		} catch (Exception e) {
			throw new LoanException(e.getMessage());
		}
		return isRequestSuccess;

	}

	/*******************************************************************************************************
	 * -Function Name :validateCustomerId(String account_Id) 
	 * -Input Parameters :String account_ID 
	 * -Return Type : boolean - Author : Rishabh Rai - Creation
	 * -Date : 24/09/2019
	 * -@throws MyException , LoanException 
	 * -Description : Validating account Id
	 ********************************************************************************************************/

	public boolean validateCustomerId(String accountId) throws LoanException{
		LoanDAO ld = new LoanDAOImpl();
		boolean isValidated = false;
		try {
			String ID = ld.fetchAccountId(accountId);
			if (ID.equals(accountId)) {
				isValidated= true;
				return isValidated;
			}
		}catch (Exception e) {
			throw new LoanException("Account Id does not exist");
		}
		
		return isValidated;

	}

}
