package com.capgemini.pecunia.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;


public interface PassbookMaintenanceService {
	public List<Transaction> updatePassbook(String accountId);
}
