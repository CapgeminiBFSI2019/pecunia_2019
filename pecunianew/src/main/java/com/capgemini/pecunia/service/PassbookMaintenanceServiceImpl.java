package com.capgemini.pecunia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dao.PassbookMaintenanceDAO;
import com.capgemini.pecunia.dao.PassbookMaintenanceDAOImpl;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;

public class PassbookMaintenanceServiceImpl implements PassbookMaintenanceService {
	
	Logger logger = Logger.getRootLogger();

	public PassbookMaintenanceServiceImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	/*******************************************************************************************************
	 * - Function Name : updatePassbook(String accountId) 
	 * - Input Parameters : String accountId
	 * - Return Type : List 
	 * - Throws : MyException 
	 * - Author : Mansi Agarwal
	 * - Creation Date : 24/09/2019 
	 * - Description : Update transaction details in passbook
	 * @throws PassbookException 
	 ********************************************************************************************************/
	
	@Override
	public List<Transaction> updatePassbook(String accountId) throws MyException, PassbookException{

		List<Transaction> transactionList = new ArrayList<Transaction>();
		PassbookMaintenanceDAO pdao = new PassbookMaintenanceDAOImpl();
		try {
			transactionList = pdao.updatePassbook(accountId);
			boolean ans=false;
			if(transactionList.size()>0) {
				ans= pdao.updateDate(accountId);
				if(ans==true)
				{
					logger.info("Updation successful");
				}
			}
		} catch (PassbookException | MyException e) {
			throw new MyException(ErrorConstants.UPDATE_PASSBOOK_ERROR);
		}
		return transactionList;
	}

	/*******************************************************************************************************
	 * - Function Name : accountSummary(String accountId, Date startDate, Date endDate) 
	 * - Input Parameters : String accountId, Date startDate, Date endDate
	 * - Return Type : List 
	 * - Throws : MyException 
	 * - Author : Rishav Dev
	 * - Creation Date : 24/09/2019 
	 * - Description : Provides the account summary
	 ********************************************************************************************************/
	
	
	
	
	@Override
	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate) throws MyException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		PassbookMaintenanceDAO pdao = new PassbookMaintenanceDAOImpl();
		try {
			transactionList = pdao.accountSummary(accountId, startDate, endDate);
		} catch (PassbookException | MyException e) {
		
			throw new MyException(ErrorConstants.UPDATE_PASSBOOK_ERROR);
		}
		return transactionList;
	}
}
