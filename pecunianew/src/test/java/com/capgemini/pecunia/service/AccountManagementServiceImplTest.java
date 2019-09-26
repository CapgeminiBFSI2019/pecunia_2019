package com.capgemini.pecunia.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;

class AccountManagementServiceImplTest {

	AccountManagementServiceImpl account;
	@BeforeEach
	void setUp() throws Exception {
		account= new AccountManagementServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		account=null;
	}

	@Test
	@DisplayName("Null input parameter")
	void testDeleteAccount() {
		
		assertThrows(AccountException.class, ()-> {account.deleteAccount(null);});
	
	}

	@Test
	@DisplayName("Null input")
	void testUpdateCustomerName() {
		assertThrows(AccountException.class, ()-> {account.updateCustomerName(null, null);});
	}

	@Test
	@DisplayName("Vidushiishurt")
	void testUpdateCustomerContact() {
		assertThrows(AccountException.class, ()-> {account.updateCustomerContact(null,null);});
	}

	@Test
	@DisplayName("Ghanta nahi chalraha")
	void testUpdateCustomerAddress() {
		assertThrows(AccountException.class, ()-> { account.updateCustomerAddress(null, null) ;});
	}

	@Test
	@DisplayName("Avizek ne reject kiya")
	void testCalculateAccountId() {
		assertThrows(AccountException.class, ()-> { account.calculateAccountId(null) ;});
	}


	@Test
	@DisplayName("Null inputs here")
	void testAddAccountNull() {
		
		assertThrows(AccountException.class, ()-> { account.addAccount(null, null, null) ;});
		
	}
	
	
	
	
	
	
	
	
}


	
	
	



