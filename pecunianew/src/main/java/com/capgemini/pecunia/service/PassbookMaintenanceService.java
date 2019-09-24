package com.capgemini.pecunia.service;

import java.util.List;
import java.util.Date;


import com.capgemini.pecunia.dto.Transaction;


public interface PassbookMaintenanceService {
	public List<Transaction> updatePassbook(String accountId);
	public List<Transaction> accountSummary(String accountId, Date startDate, Date endDate);
}
