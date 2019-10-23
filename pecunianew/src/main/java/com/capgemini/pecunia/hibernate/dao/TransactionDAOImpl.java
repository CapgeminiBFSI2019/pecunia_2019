package com.capgemini.pecunia.hibernate.dao;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getBalance(Account account) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateBalance(Account account) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int generateChequeId(Cheque cheque) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateTransactionId(Transaction transaction) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

}
