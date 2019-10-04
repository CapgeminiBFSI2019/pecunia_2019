package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dao.AccountManagementDAO;
import com.capgemini.pecunia.dao.AccountManagementDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.Constants;

public class AccountManagementServiceImpl implements AccountManagementService {

	AccountManagementDAO accountDAO;

	/*******************************************************************************************************
	 * - Function Name : deleteAccount(Account account) 
	 * - Input Parameters : Account account 
	 * - Return Type : boolean 
	 * - Throws : AccountException 
	 * - Author : Rohit Kumar 
	 * - Creation Date : 24/09/2019 
	 * - Description : Deleting an account by setting account status "Closed"
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public boolean deleteAccount(Account acc) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		try {

			boolean isValidated = validateAccountId(acc);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.deleteAccount(acc);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (Exception e) {
			throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateCustomerName(Account account, Customer customer) 
	 * - Input Parameters : Account account, Customer customer 
	 * - Return Type : boolean 
	 * - Throws : AccountException 
	 * - Author : Aditi Singh 
	 * - Creation Date : 24/09/2019 -
	 * Description : Updating customer name
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public boolean updateCustomerName(Account account, Customer customer) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		try {

			boolean isValidated = validateAccountId(account);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerName(account, customer);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}

		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateCustomerContact(Account account, Customer customer) 
	 * - Input Parameters : Account account, Customer customer 
	 * - Return Type : boolean 
	 * - Throws : AccountException 
	 * - Author : Aditi Singh 
	 * - Creation Date : 24/09/2019 -
	 * Description : Updating customer contact
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public boolean updateCustomerContact(Account account, Customer customer) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		try {

			boolean isValidated = validateAccountId(account);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerContact(account, customer);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateCustomerName(Account account, Address address) 
	 * - Input Parameters : Account account, Address address 
	 * - Return Type : boolean 
	 * - Throws : AccountException 
	 * - Author : Aditi Singh 
	 * - Creation Date : 24/09/2019 -
	 * Description : Updating customer address
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public boolean updateCustomerAddress(Account account, Address address) throws PecuniaException, AccountException {

		boolean isUpdated = false;
		try {
			boolean isValidated = validateAccountId(account);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerAddress(account, address);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : calculateAccountId(Account account)  
	 * - Input Parameters : Account account 
	 * - Return Type : String 
	 * - Throws : AccountException 
	 * - Author : Aditi Singh 
	 * - Creation Date : 24/09/2019 
	 * - Description : Generation of a new account ID with the given branch ID and type of Account
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public String calculateAccountId(Account account) throws PecuniaException, AccountException {
		try {
			String id = "";
			System.out.println("In calc acc Id service Impl");
			id = id.concat(account.getBranchId());
			System.out.println(id);
			String type = account.getAccountType();
			switch (type) {
			case Constants.SAVINGS:
				id = id.concat(Constants.CODE_SAVINGS);
				break;
			case Constants.CURRENT:
				id = id.concat(Constants.CODE_CURRENT);
				break;
			case Constants.FD:
				id = id.concat(Constants.CODE_FD);
				break;
			case Constants.LOAN:
				id = id.concat(Constants.CODE_LOAN);
				break;
			}
			System.out.println(id);
			accountDAO = new AccountManagementDAOImpl();
			account.setId(id);
			id = accountDAO.calculateAccountId(account);
			System.out.println("new id :" +id);
			return id;
		} catch (Exception e) {
			throw new AccountException(ErrorConstants.TECH_ERROR);
		}

	}

	/*******************************************************************************************************
	 * - Function Name : validateAccountId(Account account) 
	 * - Input Parameters : Account account 
	 * - Return Type : double 
	 * - Throws : AccountException 
	 * - Author : Aditi Singh 
	 * - Creation Date : 24/09/2019 
	 * - Description : Validation of Account ID
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public boolean validateAccountId(Account account) throws PecuniaException, AccountException {
		boolean isValidated = false;
		accountDAO = new AccountManagementDAOImpl();
		isValidated = accountDAO.validateAccountId(account);

		return isValidated;
	}

	/*******************************************************************************************************
	 * - Function Name : addAccount(Customer customer, Address address,Account account) 
	 * - Input Parameters : Customer customer, Address address,Account account 
	 * - Return Type : String 
	 * - Throws : AccountException 
	 * - Author : Vidushi Razdan 
	 * - Creation Date : 24/09/2019 
	 * - Description : Addition of new Account
	 * @throws PecuniaException
	 ********************************************************************************************************/

	public String addAccount(Customer customer, Address address, Account account) throws PecuniaException, AccountException {
		try {
			accountDAO = new AccountManagementDAOImpl();
			System.out.println("in account serviceimpl");
			String custId = accountDAO.addCustomerDetails(customer, address);
			System.out.println("mai hu account service impl me");
			account.setHolderId(custId);
			String accountId = calculateAccountId(account);
			System.out.println(accountId + "created");
			account.setId(accountId);
			System.out.println("mai hu abhi bhi account service me");
			String createdId = accountDAO.addAccount(account);
			System.out.println("final account Id "+createdId);
			if (createdId == null) {
				throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
			}
			return accountId;
		} catch (Exception e) {

			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		}
	}

}
