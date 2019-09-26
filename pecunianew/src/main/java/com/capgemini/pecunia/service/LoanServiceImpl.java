package com.capgemini.pecunia.service;

 
import com.capgemini.pecunia.dao.LoanDAO;
import com.capgemini.pecunia.dao.LoanDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;

 
public class LoanServiceImpl implements LoanService {
   
	public double calculateEMI(double amount, int tenure, double roi) {
		double p = amount;
		double r = roi / 1200;
		double a = Math.pow(1 + r, tenure);
		double emi = (p * r * a) / (a - 1);
		return Math.round(emi);
	}
    
    
    
	public boolean createLoanRequest(Loan loan) throws LoanException {
		boolean flag = false;
		LoanDAO ld = new LoanDAOImpl();
		try {
			ld.addLoanDetails(loan);
			flag = true;
			return flag;
		} catch (Exception e) {
			throw new LoanException(e.getMessage());
		}

	}

	

	@Override
	public boolean validateCustomerId(String account_ID) throws LoanException {
		LoanDAO ld = new LoanDAOImpl();
		boolean flag = false;
		try {
			String ID=ld.fetchAccountId(account_ID);
			if(ID.equals(account_ID)) {
				flag=true;
				return flag;
			}
		} catch (Exception e) {
			throw new LoanException(e.getMessage());
		}
		return flag;
		
	}
    
    
	
	
	
	
	
    


}


