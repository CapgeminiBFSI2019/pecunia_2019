package com.capgemini.pecunia.service;

import java.time.LocalDate;
import java.util.List;


import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;


public interface PassbookMaintenanceService {

	public List<Transaction> updatePassbook(String accountId) throws MyException, PassbookException;
	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate) throws MyException, PassbookException;

}
