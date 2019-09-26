package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
	 * - Description : Stores the transaction details in the list and returns it to service layer
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
			ps.setString(1, accountId);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Transaction details = new Transaction();
				details.setId(resultSet.getString(1));
				details.setTransDate((resultSet.getDate(2)).toLocalDate());
				details.setAmount(resultSet.getDouble(3));
				details.setTransFrom(resultSet.getString(4));
				details.setTransTo(resultSet.getString(5));
				details.setType(resultSet.getString(6));
				details.setClosingBalance(resultSet.getDouble(7));
				transactionList.add(details);

				

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

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

	/*******************************************************************************************************
	 * - Function Name : accountSummary(String accountId, Date startDate, Date endDate) 
	 * - Input Parameters : String accountId, Date startDate, Date endDate
	 * - Return Type : List 
	 * - Throws : PassbookException, MyException 
	 * - Author : Rishav Dev
	 * - Creation Date : 24/09/2019 
	 * - Description : Stores the account summary in the list and returns it to service layer
	 ********************************************************************************************************/
	
	
	
	
	
	@Override
	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate)
			throws PassbookException, MyException {

		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<Transaction> transactionList = new ArrayList<Transaction>();

		int queryResult = 0;

		try {
			ps = connection.prepareStatement(PassbookMaintenanceQueryMapper.QUERY_SUMMARY);
			ps.setString(1, accountId);
			ps.setDate(2, java.sql.Date.valueOf(startDate));
			ps.setDate(3, java.sql.Date.valueOf(endDate));
			resultSet = ps.executeQuery();
//			queryResult=ps.executeUpdate();
			while (resultSet.next()) {
				Transaction details = new Transaction();
				details.setId(resultSet.getString(1));
				details.setTransDate((resultSet.getDate(2)).toLocalDate());
				details.setAmount(resultSet.getDouble(3));
				details.setTransFrom(resultSet.getString(4));
				details.setTransTo(resultSet.getString(5));
				details.setType(resultSet.getString(6));
				details.setClosingBalance(resultSet.getDouble(7));
				transactionList.add(details);
			}

			if (queryResult == 0) {
				logger.error("updation failed ");
				throw new PassbookException(ErrorConstants.UPDATE_ACCOUNT_ERROR);

			} else {
				logger.info("updation successful:");
				return transactionList;
			}

		} catch (Exception e) {
			System.out.println("Here DAO Catch:"+e.getMessage());
			logger.error(e.getMessage());
			throw new PassbookException(ErrorConstants.TECH_ERROR);

		} finally {

			try {

				resultSet.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				logger.error(e.getMessage());
				throw new MyException(ErrorConstants.DB_CONNECTION_ERROR);

			}
		}

	}
}
