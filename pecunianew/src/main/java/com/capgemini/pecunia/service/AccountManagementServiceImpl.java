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
	 * - Function Name : deleteAccount(Account acc) - Input Parameters : Account
	 * account - Return Type : boolean - Throws : AccountException - Author : Rohit
	 * Kumar - Creation Date : 24/09/2019 - Description : Deleting an account by
	 * setting account status "Closed"
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
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
	 * - Function Name : updateCustomerName(Account acc, Customer cust) - Input
	 * Parameters : Account acc, Customer cust Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updating customer name
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public boolean updateCustomerName(Account acc, Customer cust) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		try {

			boolean isValidated = validateAccountId(acc);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerName(acc, cust);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}

		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateCustomerContact(Account acc, Customer cust) - Input
	 * Parameters : Account acc, Customer cust Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updating customer contact
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public boolean updateCustomerContact(Account acc, Customer cust) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		try {

			boolean isValidated = validateAccountId(acc);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerContact(acc, cust);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : updateCustomerName(Account acc, Address add) - Input
	 * Parameters : Account acc, Address add Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updating customer address
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public boolean updateCustomerAddress(Account acc, Address add) throws PecuniaException, AccountException {

		boolean isUpdated = false;
		try {
			boolean isValidated = validateAccountId(acc);
			if (isValidated) {
				accountDAO = new AccountManagementDAOImpl();
				isUpdated = accountDAO.updateCustomerAddress(acc, add);
			} else {
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (Exception e) {
			throw new AccountException(e.getMessage());
		}
		return isUpdated;
	}

	/*******************************************************************************************************
	 * - Function Name : calculateAccountId(Account acc) - Input Parameters :
	 * Account acc Return Type : String - Throws : AccountException - Author : Aditi
	 * Singh - Creation Date : 24/09/2019 - Description : Generation of a new
	 * account ID with the given branch ID and type of Account
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override

	public String calculateAccountId(Account acc) throws PecuniaException, AccountException {
		try {
			String id = "";
			id = id.concat(acc.getBranchId());
			String type = acc.getAccountType();
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
			accountDAO = new AccountManagementDAOImpl();
			id = accountDAO.calculateAccountId(id);
			return id;
		} catch (Exception e) {
			throw new AccountException(ErrorConstants.TECH_ERROR);
		}

	}

	/*******************************************************************************************************
	 * - Function Name : validateAccountId(Account acc) - Input Parameters : Account
	 * account - Return Type : double - Throws : AccountException - Author : Aditi
	 * Singh - Creation Date : 24/09/2019 - Description : Validation of Account ID
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public boolean validateAccountId(Account acc) throws PecuniaException, AccountException {
		boolean isValidated = false;
		accountDAO = new AccountManagementDAOImpl();
		isValidated = accountDAO.validateAccountId(acc);

		return isValidated;
	}

	/*******************************************************************************************************
	 * - Function Name : addAccount(Customer cust, Address add,Account acc) - Input
	 * Parameters : Customer cust, Address add,Account acc - Return Type : String -
	 * Throws : AccountException - Author : Vidushi Razdan - Creation Date :
	 * 24/09/2019 - Description : Addition of new Account
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public String addAccount(Customer cust, Address add, Account acc) throws PecuniaException, AccountException {
		try {
			accountDAO = new AccountManagementDAOImpl();
			String custId = accountDAO.addCustomerDetails(cust, add);

			acc.setHolderId(custId);
			String accountId = calculateAccountId(acc);
			acc.setId(accountId);

			String createdId = accountDAO.addAccount(acc);

			if (createdId == null) {
				throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
			}
			return accountId;
		} catch (Exception e) {

			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		}
	}

}
