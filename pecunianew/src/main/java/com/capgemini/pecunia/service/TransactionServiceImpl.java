package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.TransactionException;


public class TransactionServiceImpl implements TransactionService{

	/*******************************************************************************************************
	 - Function Name	:	getBalance(Account account)
	 - Input Parameters	:	Account account
	 - Return Type		:	double
	 - Throws			:  	TransactionException
	 - Author			:	Rohan Patil
	 - Creation Date	:	23/09/2019
	 - Description		:	Getting balance of the specified account
	 ********************************************************************************************************/
	
	@Override
	public double getBalance(Account account) throws TransactionException {
		return 0;
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
		/*
		 * validate
		 * existence
		 * get balance
		 * cal bal
		 * update bal
		 * create transac
		 */
		return 0;
	}

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
