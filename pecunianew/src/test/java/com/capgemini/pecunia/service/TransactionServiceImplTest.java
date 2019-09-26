package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.Constants;

class TransactionServiceImplTest {

	TransactionServiceImpl transaction;
	
	
	@BeforeEach
	void setUp() throws Exception {
		transaction = new TransactionServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		transaction=null;
	}

	@Test
	@DisplayName("Account input is null")
	void testGetBalanceNull() {
		Account acc=null;
		assertThrows(TransactionException.class, ()-> {  transaction.getBalance(acc)   ;});
	}
	
	//Dynamic 
	@Test
	@DisplayName("Valid inputs. Test case passed for getBalance()")
	void testGetBalancePass() throws TransactionException, MyException {
		Account account = new Account();
		account.setId("100202000001");
		assertEquals(20000, transaction.getBalance(account));
	}
	

	@Test
	@DisplayName("Null input.")
	void testUpdateBalanceNull() {
		Account acc=null;
		assertThrows(TransactionException.class, ()-> {  transaction.updateBalance(acc)   ;});
	}

	@Test
	@DisplayName("Valid inputs. Test case passed for updateBalance()")
	void testUpdateBalancePass() throws TransactionException, MyException {
		Account account = new Account();
		account.setId("100202000001");
		account.setBalance(20000.00);
		assertTrue(transaction.updateBalance(account));
		
	}
	
	
	@Test
	@DisplayName("Null input for credit account")
	void testCreditUsingSlipNull() {
		Transaction trans=null;
		assertThrows(TransactionException.class, ()-> {  transaction.creditUsingSlip(trans)   ;});
	}

	@Test
	@DisplayName("Valid inputs. Test case passed for creditUsingSlip()")
	void testCreditUsingSlipPass() throws TransactionException, MyException {
		Transaction tran = new Transaction();
		tran.setAccountId("100202000001");
		tran.setAmount(200.0);
		tran.setType(Constants.TRANSACTION_CREDIT);
		tran.setOption(Constants.TRANSACTION_OPTION_SLIP);
		assertNotEquals(0,transaction.creditUsingSlip(tran));
	}
	
	
	
	@Test
	@DisplayName("Null input for debit account")
	void testDebitUsingSlipNull() {
		Transaction trans=null;
		assertThrows(TransactionException.class, ()-> {  transaction.debitUsingSlip(trans)   ;});
	}

	@Test
	@DisplayName("Valid input. Test case passed for debit using slip")
	void testDebitUsingSlipPass() throws TransactionException, MyException {
		Transaction tran = new Transaction();
		tran.setAccountId("100303000001");
		tran.setAmount(500.0);
		tran.setType(Constants.TRANSACTION_DEBIT);
		tran.setOption(Constants.TRANSACTION_OPTION_SLIP);
		assertNotNull(transaction.debitUsingSlip(tran));
	}
	
	
	@Test
	@DisplayName("Null input in debit using cheque")
	void testDebitUsingChequeNull() {
		Transaction trans=null;
		Cheque cheque=null;
		assertThrows(TransactionException.class, ()-> {  transaction.debitUsingCheque(trans,cheque)   ;});
	}

	@Test
	@DisplayName("Valid inputs. Test case passed for debit using cheque")
	void testDebitUsingChequePass() throws TransactionException, MyException {
		
		LocalDate transDate=LocalDate.now(); 
		LocalDate issueDate=LocalDate.parse("2019-09-20");
		
		Transaction trans = new Transaction();
		Cheque cheque = new Cheque();
		trans.setAccountId("100303000001");
		trans.setAmount(500.0);
		trans.setOption(Constants.TRANSACTION_OPTION_CHEQUE);
		trans.setType(Constants.TRANSACTION_DEBIT);
		trans.setTransDate(transDate);

		cheque.setAccountNo("100303000001");
		cheque.setBankName(Constants.BANK_NAME);
		cheque.setHolderName("Anish Babu");
		cheque.setIfsc("PBIN0000003");
		cheque.setIssueDate(issueDate);
     
		
      assertNotNull(transaction.debitUsingCheque(trans, cheque));
	
	}

}
