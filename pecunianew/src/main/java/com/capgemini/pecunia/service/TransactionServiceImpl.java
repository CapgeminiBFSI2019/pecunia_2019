package com.capgemini.pecunia.service;

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
	 * account - Return Type : double - Throws : TransactionException - Author :
	 * Rohan Patil - Creation Date : 23/09/2019 - Description : Getting balance of
	 * the specified account
	 * 
	 * @throws MyException
	 ********************************************************************************************************/

	@Override
	public double getBalance(Account account) throws TransactionException, MyException {
		transactionDAO = new TransactionDAOImpl();
		double balance;
		balance = transactionDAO.getbalance(account);
		return balance;
	}

	@Override
	public boolean updateBalance(Account account) throws TransactionException {
		return false;
	}

	@Override
	public int creditUsingSlip(Transaction transaction) throws TransactionException {
		/*
		 */
		return 0;
	}

	@Override
	public int debitUsingSlip(Transaction transaction) throws TransactionException {
		transactionDAO = new TransactionDAOImpl();
        String accId=transaction.getAccountId();
        String transType=transaction.getType();
        double amount=transaction.getAmount();
        Date transDate=transaction.getTransDate();
        Account account=new Account(accId);
        double oldBalance=getBalance(account);
        double newBalance=0.0;
      {
                if(oldBalance>amount) {
            newBalance=oldBalance-amount;
            transaction.setClosingBalance(newBalance);
            int transId=transactionDAO.debitUsingSlip(transaction);
            
        }
        else {
            throw new TransactionException("Insufficient balance in Account");
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
