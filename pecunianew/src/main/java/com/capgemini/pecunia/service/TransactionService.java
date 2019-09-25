package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

public interface TransactionService {
	public double getBalance(Account account) throws TransactionException, MyException;
	public boolean updateBalance(Account account) throws TransactionException, MyException;
	public int creditUsingSlip(Transaction transaction) throws TransactionException, MyException;
	public int debitUsingSlip(Transaction transaction) throws TransactionException, MyException;
	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException;
	public int debitUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException;
	public double depositInterest(Account account) throws TransactionException;
	public double updateInterest() throws TransactionException, MyException;
}
