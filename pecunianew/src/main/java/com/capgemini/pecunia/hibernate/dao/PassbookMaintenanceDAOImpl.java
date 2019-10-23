package com.capgemini.pecunia.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.exception.PecuniaException;

public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO {

	@Override
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLastUpdated(String accountId) throws PecuniaException, PassbookException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate)
			throws PassbookException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

}
