package com.capgemini.pecunia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dao.PassbookMaintenanceDAO;
import com.capgemini.pecunia.dao.PassbookMaintenanceDAOImpl;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;

public class PassbookMaintenanceServiceImpl implements PassbookMaintenanceService {

	@Override
	public List<Transaction> updatePassbook(String accountId) throws MyException {

		List<Transaction> transactionList = new ArrayList<Transaction>();
		PassbookMaintenanceDAO pdao = new PassbookMaintenanceDAOImpl();
		try {
			transactionList = pdao.updatePassbook(accountId);
		} catch (PassbookException | MyException e) {
			throw new MyException(ErrorConstants.updatePassbookError);
		}
		return transactionList;
	}

	@Override
	public List<Transaction> accountSummary(String accountId, Date startDate, Date endDate) throws MyException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		PassbookMaintenanceDAO pdao = new PassbookMaintenanceDAOImpl();
		try {
			transactionList = pdao.accountSummary(accountId, startDate, endDate);
		} catch (PassbookException | MyException e) {
			throw new MyException(ErrorConstants.updatePassbookError);
		}
		return transactionList;
	}
}
