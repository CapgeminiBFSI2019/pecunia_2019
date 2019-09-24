package com.capgemini.pecunia.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dao.PassbookMaintenanceDAO;
import com.capgemini.pecunia.dao.PassbookMaintenanceDAOImpl;
import com.capgemini.pecunia.dto.Transaction;



public class PassbookMaintenanceServiceImpl implements PassbookMaintenanceService
{

	@Override
	public List<Transaction> updatePassbook(String accountId) {
		// TODO Auto-generated method stub
		List transactionList = new ArrayList<Transaction>();
		PassbookMaintenanceDAO pdao= new PassbookMaintenanceDAOImpl();
		transactionList=pdao.updatePassbook(accountId);
		return transactionList;
	}
}
