package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

public interface TransactionDAO {
	public double getbalance(Account account) throws MyException, TransactionException;
	public boolean updateBalance(Account account) throws MyException, TransactionException;

}
