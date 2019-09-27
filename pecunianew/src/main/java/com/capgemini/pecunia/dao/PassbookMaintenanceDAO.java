package com.capgemini.pecunia.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;

public interface PassbookMaintenanceDAO {
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, MyException;

	public boolean updateLastUpdated(String accountId) throws MyException, PassbookException;

	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate)
			throws PassbookException, MyException;
}
