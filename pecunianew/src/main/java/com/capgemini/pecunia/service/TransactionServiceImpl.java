package com.capgemini.pecunia.service;

import java.util.Date;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.Constants;


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
	 * Function Name : creditUsingSlip(Transaction transaction) - Input Parameters : Transaction
	 * account - Return Type : int - Throws : MyException,TransactionException - Author :
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
        Account account = new Account();
        account.setId(accId);
        double oldBalance=transactionDAO.getBalance(account);
        double newBalance=0.0;
        if(amount>=100.0) {
        
        	if(amount<=100000.0) {
            newBalance=oldBalance+amount;

            transactionDAO.updateBalance(account);
			Transaction creditTransaction=new Transaction();
			creditTransaction.setId(accId);
			creditTransaction.setAmount(amount);
			creditTransaction.setOption(Constants.TRANSACTION_OPTION_SLIP);
			creditTransaction.setType(Constants.TRANSACTION_CREDIT);
			creditTransaction.setTransDate(transDate);
			creditTransaction.setClosingBalance(newBalance);
			int transId=transactionDAO.generateTransactionId(creditTransaction);
			return transId;
			
		}
		else {
			throw new TransactionException(Constants.AMOUNT_EXCEEDS_EXCEPTION);
		}
		}
		else {
			throw new TransactionException(Constants.AMOUNT_LESS_EXCEPTION);
		}
	}


            transaction.setClosingBalance(newBalance);
            int transId=transactionDAO.generateTransactionId(transaction);
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
    /*******************************************************************************************************
	 * Function Name : debitUsingSlip(Transaction transaction) - Input Parameters : Transaction
	 * Return Type : int - Throws : TransactionException,MyException - Author :
	 * Anwesha Das - Creation Date : 24/09/2019 - Description : Debit using Slip
	 * 
	 * 
	 * @throws TransactionException,MyException
	 ********************************************************************************************************/
    
	@Override
	public int debitUsingSlip(Transaction transaction) throws TransactionException, MyException {
		transactionDAO = new TransactionDAOImpl();
        String accId=transaction.getAccountId();
        String transType=transaction.getType();
        double amount=transaction.getAmount();
        Date transDate=transaction.getTransDate();
        Account account=new Account();
        account.setId(accId);
        double oldBalance=transactionDAO.getBalance(account);
        double newBalance=0.0;
        
       
         
                if(oldBalance>amount) 
                {
                	newBalance=oldBalance-amount;
                	transactionDAO.updateBalance(account);
		            
		            Transaction debitTransaction=new Transaction();
		            debitTransaction.setId(accId);
		            debitTransaction.setAmount(amount);
		            debitTransaction.setOption(Constants.TRANSACTION_OPTION_SLIP);
		            debitTransaction.setType(Constants.TRANSACTION_DEBIT);
		            debitTransaction.setTransDate(transDate);
		            debitTransaction.setClosingBalance(newBalance);
		            int transId=transactionDAO.generateTransactionId(debitTransaction);
		            return transId;
           
                }
        else {
            throw new TransactionException("Insufficient Balance:Transaction failed");
        	}
        }
       
	
		

	@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException {
		
		return 0;
	}

	
	
	
	
	/*******************************************************************************************************
	 * Function Name : debitUsingCheque(Transaction transaction,Cheque cheque) - Input Parameters : Transaction, Cheque
	 * Return Type : int - Throws : TransactionException,MyException - Author :
	 * Anish Basu - Creation Date : 24/09/2019 - Description : Debiting using cheque
	 * 
	 * 
	 * @throws TransactionException,MyException
	 ********************************************************************************************************/
		
	@Override
	public int debitusingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException {
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
		Account account=new Account();
		account.setId(accId);
		double oldBalance=getBalance(account);
		double newBalance=0.0;
		//in milliseconds
		long diff = chequeissueDate.getTime() - transDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		if(diffDays>90 || amount>1000000.00 || amount<100.00) {
				if(oldBalance>amount) {
			newBalance=oldBalance-amount;
			transactionDAO.updateBalance(account);
			int chequeId=transactionDAO.generateChequeId(cheque);
			Transaction debitTransaction=new Transaction();
			debitTransaction.setId(accId);
			debitTransaction.setAmount(amount);
			debitTransaction.setChequeId(chequeId);
			debitTransaction.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
			debitTransaction.setType(Constants.TRANSACTION_DEBIT);
			debitTransaction.setTransDate(transDate);
			debitTransaction.setTransTo("Self");
			debitTransaction.setClosingBalance(newBalance);
			int transId=transactionDAO.generateTransactionId(debitTransaction);
			return transId;
			
		}
		else {
			throw new TransactionException(Constants.CHEQUE_BOUNCE_EXCEPTION);
		}
		}
		else {
			throw new TransactionException(Constants.INVALID_CHEQUE_EXCEPTION);
		}
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
