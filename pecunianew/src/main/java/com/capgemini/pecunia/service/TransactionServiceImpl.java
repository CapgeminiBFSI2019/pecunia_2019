package com.capgemini.pecunia.service;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.Constants;

import com.capgemini.pecunia.util.Constants;

public class TransactionServiceImpl implements TransactionService {

	TransactionDAO transactionDAO;

	/*******************************************************************************************************
	 * - Function Name : getBalance(Account account) - Input Parameters : Account
	 * account - Return Type : double - Throws : TransactionException - Author :
	 * Rohan Patil - Creation Date : 23/09/2019 - Description : Getting balance of
	 * the specified account
	 * 
	 * @throws MyException
	 ********************************************************************************************************/

	@Override
	public double getBalance(Account account) throws TransactionException, MyException {
		try {
		transactionDAO = new TransactionDAOImpl();
		double balance;
		balance = transactionDAO.getBalance(account);
		return balance;
		}catch (Exception e) {
			throw new TransactionException(ErrorConstants.FETCH_ERROR);
		}
		
	}

	@Override
	public boolean updateBalance(Account account) throws TransactionException, MyException {
		try {
		transactionDAO = new TransactionDAOImpl();
		boolean success=false;;
		success = transactionDAO.updateBalance(account);
		return success;
		}catch (Exception e) {
			throw new TransactionException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
	}

	/*******************************************************************************************************
	 * Function Name : creditUsingSlip(Transaction transaction) - Input Parameters :
	 * Transaction Return Type : int - Throws : TransactionException,MyException -
	 * Author : Arpan Mondal - Creation Date : 24/09/2019 - Description : Crediting
	 * using Slip
	 * 
	 * 
	 * @throws TransactionException,MyException
	 ********************************************************************************************************/

	@Override
	public int creditUsingSlip(Transaction transaction) throws TransactionException, MyException {

	try {	
		
		transactionDAO = new TransactionDAOImpl();

		String accId = transaction.getAccountId();
		
		double amount = transaction.getAmount();
		
		Account account = new Account();
		account.setId(accId);
		double oldBalance = transactionDAO.getBalance(account);
		double newBalance = 0.0;
		int transId = 0;
		if (amount >= Constants.MINIMUM_CREDIT_SLIP_AMOUNT) {

			if (amount <= Constants.MAXIMUM_CREDIT_SLIP_AMOUNT) {

				newBalance = oldBalance + amount;
				transaction.setClosingBalance(newBalance);
				transId = transactionDAO.generateTransactionId(transaction);
			}

			else {
				throw new TransactionException(Constants.AMOUNT_EXCEEDS_EXCEPTION);
				//TODO
				
			}
		} else {
			//TODO
			throw new TransactionException(Constants.AMOUNT_LESS_EXCEPTION);
		}
		return transId;
	}catch (Exception e) {
		throw new TransactionException(Constants.TRANSACTION_AMOUNT_ERROR);
		
	}
	}

	/*******************************************************************************************************
	 * Function Name : debitUsingSlip(Transaction transaction) - Input Parameters :
	 * Transaction Return Type : int - Throws : TransactionException,MyException -
	 * Author : Anwesha Das - Creation Date : 24/09/2019 - Description : Debit using
	 * Slip
	 * 
	 * 
	 * @throws TransactionException,MyException
	 ********************************************************************************************************/

	@Override
	public int debitUsingSlip(Transaction transaction) throws TransactionException, MyException {
		try {
			transactionDAO = new TransactionDAOImpl();
	        String accId = transaction.getAccountId();
	        double amount = transaction.getAmount();
	        LocalDate transDate = transaction.getTransDate();
	        Account account = new Account();
	        account.setId(accId);
	        double oldBalance = transactionDAO.getBalance(account);
	        System.out.println(oldBalance);
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
	            int transId = transactionDAO.generateTransactionId(debitTransaction);
	            return transId;


	        } else {
	            throw new TransactionException(Constants.INSUFFICIENT_BALANCE_EXCEPTION);
	        }
		}catch (Exception e) {
			
			throw new TransactionException(Constants.EXCEPTION_DURING_TRANSACTION);
		}
		
		
	}

	/*******************************************************************************************************
	 * Function Name : debitUsingCheque(Transaction transaction,Cheque cheque) -
	 * Input Parameters : Transaction, Cheque Return Type : int - Throws :
	 * TransactionException,MyException - Author : Anish Basu - Creation Date :
	 * 24/09/2019 - Description : Debiting using cheque
	 * 
	 * 
	 * @throws TransactionException,MyException
	 ********************************************************************************************************/

	@Override
	public int debitUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException {
		
	try {	
		
		transactionDAO = new TransactionDAOImpl();
		String accId = transaction.getAccountId();
		double amount = transaction.getAmount();
		LocalDate transDate = transaction.getTransDate();
		LocalDate chequeissueDate = cheque.getIssueDate();

		Account account = new Account();
		account.setId(accId);
		double oldBalance = getBalance(account);
		System.out.println("balanceis: " + oldBalance);
		double newBalance = 0.0;
		Period period = Period.between(chequeissueDate, transDate);
		if (period.getDays() < 90 && amount < 1000000.00 && amount > 100.00) {
			if (oldBalance > amount) {
				newBalance = oldBalance - amount;
				account.setBalance(newBalance);
				transactionDAO.updateBalance(account);
				cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
				int chequeId = transactionDAO.generateChequeId(cheque);
				Transaction debitTransaction = new Transaction();
				debitTransaction.setAccountId(accId);
				debitTransaction.setAmount(amount);
				debitTransaction.setChequeId(chequeId);
				debitTransaction.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
				debitTransaction.setType(Constants.TRANSACTION_DEBIT);
				debitTransaction.setTransDate(transDate);
				debitTransaction.setTransTo(Constants.SELF_CHEQUE);
				debitTransaction.setClosingBalance(newBalance);
				int transId = transactionDAO.generateTransactionId(debitTransaction);
				return transId;

			} else {
				//TODO logger
				throw new TransactionException(Constants.CHEQUE_BOUNCE_EXCEPTION);
			}
		} else {
			//TODO logger
			throw new TransactionException(Constants.INVALID_CHEQUE_EXCEPTION);
		}
	}catch (Exception e) {
		throw new TransactionException(Constants.EXCEPTION_DURING_TRANSACTION);
	}
	}

	@Override
	public double depositInterest(Account account) throws TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override

	public double updateInterest() throws TransactionException, MyException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int creditUsingCheque(Transaction transaction, Cheque cheque) throws TransactionException, MyException {
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

		int transId = 0;

		if ((bankName != Constants.BANK_NAME) && (Arrays.asList(Constants.OTHER_BANK_NAME).contains(bankName))) {
			// other banks cheque
			chequeDetail.setStatus(Constants.CHEQUE_STATUS_PENDING);
			transId = transactionDAO.generateChequeId(chequeDetail);
		} else {
			if (bankName != Constants.BANK_NAME) {
				// invalid bank cheque
				throw new TransactionException(Constants.INVALID_BANK_EXCEPTION);
			} else {
				// pecunia cheque
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

					transactionDAO.updateBalance(payeeAccount);
					transactionDAO.updateBalance(beneficiaryAccount);

				}
			}
		}
		return transId;

	}

}
