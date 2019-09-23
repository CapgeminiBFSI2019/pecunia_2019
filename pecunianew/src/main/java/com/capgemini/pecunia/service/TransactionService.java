package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.TransactionException;

public interface TransactionService {
	public double getBalance(Account account) throws TransactionException;
	public boolean updateBalance(Account account) throws TransactionException;
	public int creditUsingSlip(Transaction transaction) throws TransactionException;
	public int debitUsingSlip(Transaction transaction) throws TransactionException;
	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException;
	public int debitusingCheque(Transaction transaction, Cheque cheque) throws TransactionException;
	public double depositInterest(Account account) throws TransactionException;
	public double updateInterest() throws TransactionException;
}
