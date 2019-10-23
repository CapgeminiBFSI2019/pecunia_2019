package com.capgemini.pecunia.hibernate.dao;

import java.sql.SQLException;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	@Override
	public boolean deleteAccount(Account account) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerName(Account account, Customer customer) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerContact(Account account, Customer customer) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerAddress(Account account, Address address) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String addCustomerDetails(Customer customer, Address address)
			throws PecuniaException, AccountException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAccount(Account account) throws PecuniaException, AccountException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculateAccountId(Account account) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateAccountId(Account account) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAccountError(Account account) throws PecuniaException, SQLException, AccountException {
		// TODO Auto-generated method stub

	}

}
