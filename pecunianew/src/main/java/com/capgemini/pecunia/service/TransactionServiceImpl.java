package com.capgemini.pecunia.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.Constants;

public class TransactionServiceImpl implements TransactionService {

	TransactionDAO transactionDAO;

	/*******************************************************************************************************
	 * - Function Name : getBalance(Account account) - Input Parameters : account
	 * object - Return Type : double - Throws : TransactionException,MyException -
	 * Author : Rohan Patil,Anwesha Das - Creation Date : 23/09/2019 - Description :
	 * Getting balance of the specified account
	 ********************************************************************************************************/

	@Override
	public double getBalance(Account account) throws TransactionException, MyException {
		try {
			transactionDAO = new TransactionDAOImpl();
			double balance;
			balance = transactionDAO.getBalance(account);
			return balance;
		} catch (Exception e) {
			throw new TransactionException(ErrorConstants.FETCH_ERROR);
		}

	}

	/*******************************************************************************************************
	 * - Function Name : updateBalance(Account account) - Input Parameters : account
	 * object - Return Type : boolean - Throws : TransactionException,MyException -
	 * Author : Anish Basu, Arpan Mondal - Creation Date : 23/09/2019 - Description
	 * : update balance of the specified account
	 ********************************************************************************************************/

	@Override
	public boolean updateBalance(Account account) throws TransactionException, MyException {
		try {
			transactionDAO = new TransactionDAOImpl();
			boolean success = false;
			success = transactionDAO.updateBalance(account);
			return success;
		} catch (Exception e) {
			throw new TransactionException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
	}

	/*******************************************************************************************************
	 * - Function Name : creditUsingSlip(Transaction transaction) - Input Parameters
	 * : transaction object - Return Type : int - Throws :
	 * TransactionException,MyException - Author : Arpan Mondal - Creation Date :
	 * 23/09/2019 - Description : crediting amount using slip of the specified
	 * account
	 ********************************************************************************************************/

	@Override
	public int creditUsingSlip(Transaction transaction) throws TransactionException, MyException {

		int transId = 0;
		try {

			transactionDAO = new TransactionDAOImpl();

			String accId = transaction.getAccountId();

			double amount = transaction.getAmount();

			Account account = new Account();
			account.setId(accId);
			double oldBalance = transactionDAO.getBalance(account);
			double newBalance = 0.0;
			if (amount >= Constants.MINIMUM_CREDIT_SLIP_AMOUNT) {

				if (amount <= Constants.MAXIMUM_CREDIT_SLIP_AMOUNT) {

					newBalance = oldBalance + amount;
					account.setBalance(newBalance);
					transactionDAO.updateBalance(account);
					transaction.setClosingBalance(newBalance);
					transaction.setType(Constants.TRANSACTION_CREDIT);
					transaction.setOption(Constants.TRANSACTION_OPTION_SLIP);
					transaction.setTransTo(Constants.NA);
					transaction.setTransFrom(Constants.NA);
					transId = transactionDAO.generateTransactionId(transaction);

				}

				else {
					throw new TransactionException(Constants.AMOUNT_EXCEEDS_EXCEPTION);
					// TODO

				}
			} else {
				// TODO
				throw new TransactionException(Constants.AMOUNT_LESS_EXCEPTION);
			}
		} catch (TransactionException e) {
			e.getMessage();
			throw new TransactionException(e.getMessage());
		}

		catch (Exception e) {
			throw new TransactionException(Constants.TRANSACTION_AMOUNT_ERROR);

		}
		return transId;
	}

	/*******************************************************************************************************
	 * - Function Name : debitUsingSlip(Transaction transaction) - Input Parameters
	 * : transaction object - Return Type : int - Throws :
	 * TransactionException,MyException - Author : Anwesha Das - Creation Date :
	 * 23/09/2019 - Description : debiting amount using slip of the specified
	 * account
	 ********************************************************************************************************/

	@Override
	public int debitUsingSlip(Transaction transaction) throws TransactionException, MyException {

		int transId = 0;
		try {
			transactionDAO = new TransactionDAOImpl();
			String accId = transaction.getAccountId();
			double amount = transaction.getAmount();
			LocalDate transDate = transaction.getTransDate();
			Account account = new Account();
			account.setId(accId);
			double oldBalance = transactionDAO.getBalance(account);

			double newBalance = 0.0;

			if (oldBalance > amount) {
				newBalance = oldBalance - amount;
				account.setBalance(newBalance);
				transactionDAO.updateBalance(account);
				Transaction debitTransaction = new Transaction();
				debitTransaction.setAccountId(accId);
				debitTransaction.setAmount(amount);
				debitTransaction.setOption(Constants.TRANSACTION_OPTION_SLIP);
				debitTransaction.setType(Constants.TRANSACTION_DEBIT);
				debitTransaction.setTransDate(transDate);
				debitTransaction.setClosingBalance(newBalance);
				debitTransaction.setTransTo(Constants.NA);
				debitTransaction.setTransFrom(Constants.NA);
				transId = transactionDAO.generateTransactionId(debitTransaction);

			} else {
				throw new TransactionException(Constants.INSUFFICIENT_BALANCE_EXCEPTION);
			}
		} catch (TransactionException e) {
			e.getMessage();
			throw new TransactionException(e.getMessage());
		} catch (Exception e) {

			throw new TransactionException(Constants.EXCEPTION_DURING_TRANSACTION);

		}
		return transId;

	}

	/*******************************************************************************************************
	 * - Function Name : debitUsingCheque(Transaction transaction,Cheque cheque) -
	 * Input Parameters : transaction object, cheque object - Return Type : int -
	 * Throws : TransactionException,MyException - Author : Anish Basu - Creation
	 * Date : 23/09/2019 - Description : debiting amount using cheque of the
	 * specified account
	 ********************************************************************************************************/

	@Override
	public int debitUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException {
		int transId = 0;
		int chequeId = 0;
		try {

			transactionDAO = new TransactionDAOImpl();
			String accId = transaction.getAccountId();
			double amount = transaction.getAmount();
			LocalDate transDate = LocalDate.now();
			LocalDate chequeissueDate = cheque.getIssueDate();
			Account account = new Account();
			account.setId(accId);
			double oldBalance = getBalance(account);
			double newBalance = 0.0;
			long period = ChronoUnit.DAYS.between(chequeissueDate, transDate);

			if (period <= 90 && amount <= Constants.MAXIMUM_CHEQUE_AMOUNT
					&& amount >= Constants.MINIMUM_CHEQUE_AMOUNT) {
				if (oldBalance > amount) {
					newBalance = oldBalance - amount;
					account.setBalance(newBalance);
					transactionDAO.updateBalance(account);
					cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
					cheque.setBankName(Constants.BANK_NAME);
					chequeId = transactionDAO.generateChequeId(cheque);
					Transaction debitTransaction = new Transaction();
					debitTransaction.setAccountId(accId);
					debitTransaction.setAmount(amount);
					debitTransaction.setChequeId(chequeId);
					debitTransaction.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
					debitTransaction.setType(Constants.TRANSACTION_DEBIT);
					debitTransaction.setTransDate(transDate);
					debitTransaction.setTransTo(Constants.SELF_CHEQUE);
					debitTransaction.setTransFrom(Constants.NA);
					debitTransaction.setClosingBalance(newBalance);
					transId = transactionDAO.generateTransactionId(debitTransaction);

				} else {
					// TODO logger
					throw new TransactionException(Constants.CHEQUE_BOUNCE_EXCEPTION);
				}
			} else {
				// TODO logger
				throw new TransactionException(Constants.INVALID_CHEQUE_EXCEPTION);
			}

		} catch (TransactionException e) {
			e.getMessage();
			throw new TransactionException(e.getMessage());
		} catch (Exception e) {
			throw new TransactionException(Constants.EXCEPTION_DURING_TRANSACTION);
		}
		return transId;
	}

	/*******************************************************************************************************
	 * - Function Name : depositInterest(Account account) - Input Parameters :
	 * account object - Return Type : - Throws : TransactionException - Author : -
	 * Creation Date : 23/09/2019 - Description : deposit interest of the specified
	 * account
	 ********************************************************************************************************/

	@Override
	public double depositInterest(Account account) throws TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*******************************************************************************************************
	 * - Function Name : updateInterest() - Input Parameters : account object -
	 * Return Type : - Throws : TransactionException, MyException - Author : -
	 * Creation Date : 23/09/2019 - Description : update interest of the specified
	 * account
	 ********************************************************************************************************/
	@Override

	public double updateInterest() throws TransactionException, MyException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*******************************************************************************************************
	 * - Function Name : creditUsingCheque(Transaction transaction, Cheque cheque) -
	 * Input Parameters : transaction object, cheque object - Return Type : int -
	 * Throws : TransactionException,MyException - Author : Rohan Patil - Creation
	 * Date : 23/09/2019 - Description : crediting amount using cheque of the
	 * specified account
	 ********************************************************************************************************/

	@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException {
		int transId = 0;

		try {
			double beneficiaryBalance = 0, payeeBalance = 0, newBeneficiaryBalance = 0, newPayeeBalance = 0;

			String bankName = cheque.getBankName();

			Transaction creditTransaction, debitTransaction;

			Cheque chequeDetail;
			chequeDetail = new Cheque();
			chequeDetail.setNum(cheque.getNum());
			chequeDetail.setAccountNo(cheque.getAccountNo());
			chequeDetail.setBankName(cheque.getBankName());
			chequeDetail.setHolderName(cheque.getHolderName());
			chequeDetail.setIfsc(cheque.getIfsc());
			chequeDetail.setIssueDate(cheque.getIssueDate());

			TransactionDAO transactionDAO = new TransactionDAOImpl();

			if ((bankName != Constants.BANK_NAME) && (Arrays.asList(Constants.OTHER_BANK_NAME).contains(bankName))) {
				// other banks cheque
				chequeDetail.setStatus(Constants.CHEQUE_STATUS_PENDING);
				transId = transactionDAO.generateChequeId(chequeDetail);
			} else {

				if (!bankName.equals(Constants.BANK_NAME)) {
					// invalid bank cheque
					throw new TransactionException(Constants.INVALID_BANK_EXCEPTION);

				// pecunia cheque
				if (transaction.getAmount() < Constants.MINIMUM_CHEQUE_AMOUNT
						|| transaction.getAmount() > Constants.MAXIMUM_CHEQUE_AMOUNT) {
					// invalid cheque amount
					throw new TransactionException(Constants.INVALID_CHEQUE_EXCEPTION);

				} else {
					// pecunia cheque
					if (transaction.getAmount() < Constants.MINIMUM_CHEQUE__AMOUNT
							|| transaction.getAmount() > Constants.MAXIMUM_CHEQUE__AMOUNT) {

				if (!bankName.equals(Constants.BANK_NAME)) {
					// invalid bank cheque
					throw new TransactionException(Constants.INVALID_BANK_EXCEPTION);
				} else {
					// pecunia cheque
					if (transaction.getAmount() < Constants.MINIMUM_CHEQUE_AMOUNT
							|| transaction.getAmount() > Constants.MAXIMUM_CHEQUE_AMOUNT) {

						// invalid cheque amount
						throw new TransactionException(Constants.INVALID_CHEQUE_EXCEPTION);
					} else {

						Account beneficiaryAccount = new Account();
						beneficiaryAccount.setId(transaction.getAccountId());

						Account payeeAccount = new Account();
						payeeAccount.setId(transaction.getTransFrom());

						beneficiaryBalance = transactionDAO.getBalance(beneficiaryAccount);
						payeeBalance = transactionDAO.getBalance(payeeAccount);

						if (payeeBalance < transaction.getAmount()) {
							// cheque bounce
							chequeDetail.setStatus(Constants.CHEQUE_STATUS_BOUNCED);
							transId = transactionDAO.generateChequeId(chequeDetail);
						} else {
							chequeDetail.setStatus(Constants.CHEQUE_STATUS_CLEARED);
							int chequeId = transactionDAO.generateChequeId(chequeDetail);

							newBeneficiaryBalance = beneficiaryBalance + transaction.getAmount();
							newPayeeBalance = payeeBalance - transaction.getAmount();

							beneficiaryAccount.setBalance(newBeneficiaryBalance);
							payeeAccount.setBalance(newPayeeBalance);

							creditTransaction = new Transaction();
							creditTransaction.setAccountId(transaction.getAccountId());
							creditTransaction.setType(Constants.TRANSACTION_CREDIT);
							creditTransaction.setAmount(transaction.getAmount());
							creditTransaction.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
							creditTransaction.setChequeId(chequeId);
							creditTransaction.setTransFrom(transaction.getTransFrom());
							creditTransaction.setTransTo(Constants.NA);
							creditTransaction.setClosingBalance(newBeneficiaryBalance);

							debitTransaction = new Transaction();
							debitTransaction.setAccountId(transaction.getTransFrom());
							debitTransaction.setType(Constants.TRANSACTION_DEBIT);
							debitTransaction.setAmount(transaction.getAmount());
							debitTransaction.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
							debitTransaction.setChequeId(chequeId);
							debitTransaction.setTransFrom(Constants.NA);
							debitTransaction.setTransTo(transaction.getAccountId());
							debitTransaction.setClosingBalance(newPayeeBalance);

							transId = transactionDAO.generateTransactionId(debitTransaction);
							transId = transactionDAO.generateTransactionId(creditTransaction);

							transactionDAO.updateBalance(beneficiaryAccount);
							transactionDAO.updateBalance(payeeAccount);
						}
					}
				}
			}
			return transId;
		} catch (Exception e) {

			System.out.println(e.getMessage());


			throw new TransactionException(Constants.EXCEPTION_DURING_TRANSACTION);
		}

	}

}
