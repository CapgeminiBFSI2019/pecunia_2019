package com.capgemini.pecunia.hibernate.dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.query.Query;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.entity.AccountEntity;
import com.capgemini.pecunia.entity.AddressEntity;
import com.capgemini.pecunia.entity.CustomerEntity;
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
			String hql = "from AccountEntity where accountId= :accountId";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", account.getId());
			query.setMaxResults(1);
			AccountEntity accountEntity = (AccountEntity) query.uniqueResult();
			if (accountEntity != null) {
				custId = accountEntity.getCustomerId();
			}
			Transaction txn = session.beginTransaction();
			String hqlUpdate = "UPDATE CustomerEntity SET name=:name WHERE customerId=:customerId";
			Query queryUpdate = session.createQuery(hqlUpdate);
			queryUpdate.setParameter("name", customer.getName());
			queryUpdate.setParameter("customerId", custId);
			query.setMaxResults(1);
			int rowsAffected = queryUpdate.executeUpdate();
			if (rowsAffected > 0) {
			    isUpdated=true;
				txn.commit();
			}
			else {
				throw new PecuniaException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		return isUpdated;
	}

	@Override
	public boolean updateCustomerContact(Account account, Customer customer) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		String custId = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "from AccountEntity where accountId= :accountId";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", account.getId());
			query.setMaxResults(1);
			AccountEntity accountEntity = (AccountEntity) query.uniqueResult();
			if (accountEntity != null) {
				custId = accountEntity.getCustomerId();
			}
			Transaction txn = session.beginTransaction();
			String hqlUpdate = "UPDATE CustomerEntity SET contact=:contact WHERE customerId=:customerId";
			Query queryUpdate = session.createQuery(hqlUpdate);
			queryUpdate.setParameter("contact", customer.getContact());
			queryUpdate.setParameter("customerId", custId);
			query.setMaxResults(1);
			int rowsAffected = queryUpdate.executeUpdate();
			if (rowsAffected > 0) {
			    isUpdated=true;
				txn.commit();
			}
			else {
				throw new PecuniaException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		return isUpdated;
	}

	@Override
	public boolean updateCustomerAddress(Account account, Address address) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		String addrId = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "SELECT address.address_id from customer INNER JOIN address ON address.address_id=customer.address_id"+ 
					       "INNER JOIN account ON account.customer_id=customer.customer_id WHERE account_id=?";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", account.getId());
			query.setMaxResults(1);
			AccountEntity accountEntity = (AccountEntity) query.uniqueResult();
			if (accountEntity != null) {
				addrId = accountEntity.getCustomerId();
			}
			Transaction txn = session.beginTransaction();
			String hqlUpdate = "UPDATE AddressEntity SET address_line1=:line1, address_line2=:line2, city=:city, state=:state,country=:country, zipcode=:zipcode WHERE addressId=:addressId";
			Query queryUpdate = session.createQuery(hqlUpdate);
			queryUpdate.setParameter("addressId", addrId);
			queryUpdate.setParameter("line1",address.getLine1());
			queryUpdate.setParameter("line2",address.getLine2());
			queryUpdate.setParameter("city",address.getCity());
			queryUpdate.setParameter("state",address.getState());
			queryUpdate.setParameter("country",address.getCountry());
			queryUpdate.setParameter("zipcode",address.getZipcode());
			query.setMaxResults(1);
			int rowsAffected = queryUpdate.executeUpdate();
			if (rowsAffected > 0) {
			    isUpdated=true;
				txn.commit();
			}
			else {
				throw new PecuniaException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		return isUpdated;
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

		// Add new Employee object
		AccountEntity acc = new AccountEntity();

		acc.setCustomerId(account.getHolderId());
		acc.setBranchId(account.getBranchId());
		acc.setType(account.getAccountType());
		acc.setStatus(account.getStatus());
//	     acc.setBalance(account.getBalance());
//	     acc.setInterest(account.getInterest());
//	    acc.setLastUpdated(account.getLastUpdated());
//	    acc.getAccountId();

		// Save the employee in database
		session.save(acc);

		// Commit the transaction
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
