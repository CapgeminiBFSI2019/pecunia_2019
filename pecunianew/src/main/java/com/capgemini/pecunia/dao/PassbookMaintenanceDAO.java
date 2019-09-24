package com.capgemini.pecunia.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;

public interface PassbookMaintenanceDAO {
	public List<Transaction> updatePassbook(String accountId);

	public List<Transaction> accountSummary(String accountId, Date startDate, Date endDate);
}
