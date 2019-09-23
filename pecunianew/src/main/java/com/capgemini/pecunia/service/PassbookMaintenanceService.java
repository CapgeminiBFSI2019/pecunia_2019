package com.capgemini.pecunia.service;

import java.util.ArrayList;

import com.capgemini.pecunia.dto.Transaction;


public interface PassbookMaintenanceService {
	public ArrayList<Transaction> updatePassbook(String accountId);
}
