package com.capgemini.pecunia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO
{
	@Override
	public boolean updatePassbook(String accountId) 
	{
		boolean updated = false;
		//Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		return updated;
	}
}
