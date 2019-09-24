package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.util.DBConnection;



public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO
{
	@Override
	public List<Transaction> updatePassbook(String accountId) 
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		PreparedStatement ps=connection.prepareStatement(PassbookMaintenanceQueryMapper.QUERY_TRANS);
	    ResultSet resultSet = null;
	    List<Transaction> transactionList=new ArrayList<Transaction>();
        try
        {
				
		resultSet=ps.executeQuery();
		 while(resultSet.next())
         {
			 Transaction details = new Transaction();
			 details.setId(resultSet.getString(1));
			 details.setTransDate(resultSet.getDate(2));
			 details.setAmount(resultSet.getDouble(3));
			 details.setTransFrom(resultSet.getString(4));
			 details.setTransTo(resultSet.getString(5));
			 details.setClosingBalance(resultSet.getDouble(6));
			 transactionList.add(details);
         }
        }catch (SQLException sqlException) 
        {}
        finally
        {
            try 
            {
                resultSet.close();
                ps.close();
                connection.close();
            } 
            catch (SQLException e) 
            {}
        }
		return transactionList;
		
		
	}
	
	
	
	
	
	
	@Override
	public List<Transaction> accountSummary(String accountId, Date startDate, Date endDate) 
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		PreparedStatement ps=connection.prepareStatement(PassbookMaintenanceQueryMapper.QUERY_SUMMARY);
	    ResultSet resultSet = null;
	    List<Transaction> transactionList=new ArrayList<Transaction>();
        try
        {
				
		resultSet=ps.executeQuery();
		 while(resultSet.next())
         {
			 Transaction details = new Transaction();
			 details.setId(resultSet.getString(1));
			 details.setTransDate(resultSet.getDate(2));
			 details.setAmount(resultSet.getDouble(3));
			 details.setTransFrom(resultSet.getString(4));
			 details.setTransTo(resultSet.getString(5));
			 details.setClosingBalance(resultSet.getDouble(6));
			 transactionList.add(details);
         }
        }catch (SQLException sqlException) 
        {}
        finally
        {
            try 
            {
                resultSet.close();
                ps.close();
                connection.close();
            } 
            catch (SQLException e) 
            {}
        }
		return transactionList;
		
		
	}
}
