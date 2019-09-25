package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.util.DBConnection;

public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO {

	Logger logger = Logger.getRootLogger();

	public PassbookMaintenanceDAOImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	/*******************************************************************************************************
	 * - Function Name : updatePassbook(String accountId) 
	 * - Input Parameters : String accountId
	 * - Return Type : List 
	 * - Throws : PassbookException, MyException 
	 * - Author : Mansi Agarwal
	 * - Creation Date : 24/09/2019 
	 * - Description : Stores the transaction details in the list 
	 ********************************************************************************************************/
	
	@Override
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, MyException {

		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<Transaction> transactionList = new ArrayList<Transaction>();

		int queryResult = 0;

		try {
			ps = connection.prepareStatement(PassbookMaintenanceQueryMapper.QUERY_TRANS_DETAILS);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Transaction details = new Transaction();
				details.setId(resultSet.getString(1));
				details.setTransDate(resultSet.getDate(2));
				details.setAmount(resultSet.getDouble(3));
				details.setTransFrom(resultSet.getString(4));
				details.setTransTo(resultSet.getString(5));
				details.setClosingBalance(resultSet.getDouble(6));
				transactionList.add(details);

				if (queryResult == 0) {
					logger.error("updation failed ");
					throw new PassbookException("updation failed ");

				} else {
					logger.info("updation successful:");
					return transactionList;
				}

			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			throw new PassbookException(ErrorConstants.TECH_ERROR);

		} finally {

			try {

				resultSet.close();
				ps.close();
				connection.close();
			} catch (Exception e) {

				logger.error(e.getMessage());
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);

			}
		}

		return transactionList;

	}

	@Override
	public List<Transaction> accountSummary(String accountId, Date startDate, Date endDate)
			throws PassbookException, MyException {

		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<Transaction> transactionList = new ArrayList<Transaction>();

		int queryResult = 0;

		try {
			ps = connection.prepareStatement(PassbookMaintenanceQueryMapper.QUERY_SUMMARY);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Transaction details = new Transaction();
				details.setId(resultSet.getString(1));
				details.setTransDate(resultSet.getDate(2));
				details.setAmount(resultSet.getDouble(3));
				details.setTransFrom(resultSet.getString(4));
				details.setTransTo(resultSet.getString(5));
				details.setClosingBalance(resultSet.getDouble(6));
				transactionList.add(details);
			}

			if (queryResult == 0) {
				logger.error("updation failed ");
				throw new PassbookException("updation failed ");

			} else {
				logger.info("updation successful:");
				return transactionList;
			}

		} catch (Exception e) {

			logger.error(e.getMessage());
			throw new PassbookException(ErrorConstants.TECH_ERROR);

		} finally {

			try {

				resultSet.close();
				ps.close();
				connection.close();
			} catch (Exception e) {

				logger.error(e.getMessage());
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);

			}
		}

	}
}
