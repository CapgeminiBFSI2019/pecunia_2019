package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

public interface TransactionDAO {
	public double getBalance(Account account) throws MyException, TransactionException;
	public boolean updateBalance(Account account) throws MyException, TransactionException;

	public int creditUsingSlip(Transaction transaction) throws TransactionException, MyException;

	public int generateChequeId(Cheque cheque) throws MyException, TransactionException;
	public int generateTransactionId(Transaction transaction) throws MyException, TransactionException;
}
