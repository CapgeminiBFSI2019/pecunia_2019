package com.capgemini.pecunia.service;

import java.util.Date;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

public class TransactionServiceImpl implements TransactionService {

	TransactionDAO transactionDAO;

	/*******************************************************************************************************
	 * - Function Name : getBalance(Account account) - Input Parameters : Account
	 * account - Return Type : double - Throws : TransactionException - 
	 * Author : Rohan Patil - Creation Date : 23/09/2019 - Description : Getting balance of
	 * the specified account
	 * 
	 * @throws MyException
	 ********************************************************************************************************/

	@Override
	public double getBalance(Account account) throws TransactionException, MyException {
		transactionDAO = new TransactionDAOImpl();
		double balance;
		balance = transactionDAO.getBalance(account);
		return balance;
	}

	@Override
	public boolean updateBalance(Account account) throws TransactionException, MyException {
		transactionDAO = new TransactionDAOImpl();
		boolean success;
		success = transactionDAO.updateBalance(account);
		return success;
	}

	/*******************************************************************************************************
	 * Function Name : creditUsingSlip(Transaction transaction) - Input Parameters : AccountId,amount,date
	 * account - Return Type : int - Throws : TransactionException - Author :
	 * Arpan Mondal - Creation Date : 23/09/2019 - Description : Crediting using slip
	 * 
	 * 
	 * @throws MyException
	 ********************************************************************************************************/
	
	@Override
	public int creditUsingSlip(Transaction transaction) throws TransactionException, MyException {
		
		
		transactionDAO = new TransactionDAOImpl();
        String accId=transaction.getAccountId();
        String transType=transaction.getType();
        double amount=transaction.getAmount();
        Date transDate=transaction.getTransDate();
        Account acc=new Account();
        acc.setId(accId);
        double oldBalance=transactionDAO.getBalance(acc);
        double newBalance=0.0;
        if(amount>=100.0) {
        
        	if(amount<=100000.0) {
        	
            newBalance=oldBalance+amount;
            transaction.setClosingBalance(newBalance);
            int transId=transactionDAO.creditUsingSlip(transaction);
        	}
        	
        	else{
        		throw new TransactionException("Amount exceeds the limit");
        	}
        }
        else {
            throw new TransactionException("Amount is too less");
        }
        return 0;
    }
		 

	@Override
	public int debitUsingSlip(Transaction transaction) throws TransactionException {
		transactionDAO = new TransactionDAOImpl();
        String accId=transaction.getAccountId();
        String transType=transaction.getType();
        double amount=transaction.getAmount();
        Date transDate=transaction.getTransDate();
        Account account=new Account();
        account.setId(accId);
        double oldBalance=getBalance(account);
        double newBalance=0.0;
        
       
         
                if(oldBalance>amount) 
                {
                	newBalance=oldBalance-amount;
                	transactionDAO.updateBalance(account);
		            
		            Transaction debitTransaction=new Transaction();
		            debitTransaction.setId(accId);
		            debitTransaction.setAmount(amount);
		            debitTransaction.setOption(Values.TRANSACTION_OPTION_CHEQUE);
		            debitTransaction.setType(Values.TRANSACTION_DEBIT);
		            debitTransaction.setTransDate(transDate);
		            debitTransaction.setClosingBalance(newBalance);
		            int transId=transactionDAO.generateTransactionId(debitTransaction);
		            return transId;
           
                }
        else {
            throw new TransactionException("Insufficient Balance:Transaction failed");
        	}
        }
       
	
		/*
		 * validate existence get balance cal bal update bal create transac
		 */

	@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException {
		
		return 0;
	}

	@Override
	public int debitusingCheque(Transaction transaction, Cheque cheque) throws TransactionException {
		transactionDAO = new TransactionDAOImpl();
		String accId=transaction.getAccountId();
		String transType=transaction.getType();
		double amount=transaction.getAmount();
		Date transDate=transaction.getTransDate();
		Date chequeissueDate=cheque.getIssueDate();
		int chequeNum=cheque.getNum();
		String holderName=cheque.getHolderName();
		String bankName=cheque.getBankName();
		String ifsc=cheque.getIfsc();
		//String status=cheque.getStatus();
		Account account=new Account(accId,Values.NA,Values.NA,Values.NA,Values.NA,Values.NA,Values.NA,Values.NA);
		double oldBalance=getBalance(account);
		double newBalance=0.0;
		//in milliseconds
		long diff = chequeissueDate.getTime() - transDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		if(diffDays>90) {
				if(oldBalance>amount) {
			newBalance=oldBalance-amount;
			//transaction.setChequeId(chequeId);
			transaction.setClosingBalance(newBalance);
			int transId=transactionDAO.debitusingCheque(transaction, cheque);
			
		}
		else {
			throw new TransactionException("Insufficient balance in Account");
		}
		}
		else {
			throw new TransactionException("Cheque is Invalid");
		}
		return 0;
	}

	@Override
	public double depositInterest(Account account) throws TransactionException {
		return 0;
	}

	@Override
	public double updateInterest() throws TransactionException {
		return 0;
	}

}
