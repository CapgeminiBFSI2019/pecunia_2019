package com.capgemini.pecunia.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;



public interface PassbookMaintenanceDAO  {
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, MyException;

}
