package com.capgemini.pecunia.hibernate.dao;

import java.sql.SQLException;

import org.hibernate.Session;
//import org.hibernate.query.Query;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.entity.AccountEntity;
import com.capgemini.pecunia.entity.AddressEntity;
import com.capgemini.pecunia.entity.CustomerEntity;
import com.capgemini.pecunia.entity.TransactionEntity;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.HibernateUtil;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	@Override
	public boolean deleteAccount(Account account) throws PecuniaException, AccountException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerName(Account account, Customer customer) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		String custId = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("session value : "+session);
			String hql = "from AccountEntity where accountId= :accountId";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", account.getId());
			query.setMaxResults(1);
			AccountEntity accountEntity = (AccountEntity) query.uniqueResult();
			if(accountEntity != null)
			{
				System.out.println("Customer id : "+accountEntity.getCustomerId());
			}
//			custId = (String) query.uniqueResult();
			System.out.println("Customer Id: "+custId);
//			String hqlUpdate = "UPDATE customer SET name=:name WHERE customerId=:customerId";
//			Query queryUpdate = session.createQuery(hqlUpdate);
//			queryUpdate.setParameter("name", customer.getName());
//			queryUpdate.setParameter("customerId", custId);
//			int rowsAffected = queryUpdate.executeUpdate();
//			System.out.println(rowsAffected);
//			if (rowsAffected > 0) {
//			    isUpdated=true;
//			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		return isUpdated;
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
		 Session session = HibernateUtil.getSessionFactory().openSession();
	     session.beginTransaction();
	     CustomerEntity cust = new CustomerEntity(null, null, null, null, null, null, null);
	     AddressEntity addr = new AddressEntity(null, null, null, null, null, null);
	     cust.setAadhar(customer.getAadhar());
	     cust.setName(customer.getName());
	     cust.setGender(customer.getGender());
	     cust.setContact(customer.getContact());
//	     cust.setDob(customer.getDob());
	     cust.setPan(customer.getPan());
//	     cust.getAddressId();
	  
	     addr.setAddressLine1(address.getLine1());
	     addr.setAddressLine2(address.getLine2());
	     addr.setCity(address.getCity());
	     addr.setState(address.getState());
	     addr.setCountry(address.getCountry());
	     addr.setZipcode(address.getZipcode());
	     session.save(cust);
	     session.save(addr);
	     session.getTransaction().commit();
//	     HibernateUtil.shutdown();
		return null;
	}

	@Override
	public String addAccount(Account account) throws PecuniaException, AccountException, SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
	     session.beginTransaction();
	     
	     //Add new Employee object
	     AccountEntity acc = new  AccountEntity(null, null, null, null, null, null, null);

	     acc.setCustomerId(account.getHolderId());
	     acc.setBranchId(account.getBranchId());
	     acc.setType(account.getAccountType());
	     acc.setStatus(account.getStatus());
//	     acc.setBalance(account.getBalance());
//	     acc.setInterest(account.getInterest());
//	    acc.setLastUpdated(account.getLastUpdated());
//	    acc.getAccountId();
	      
	     //Save the employee in database
	     session.save(acc);

	     //Commit the transaction
	     session.getTransaction().commit();
//	     HibernateUtil.shutdown();
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
