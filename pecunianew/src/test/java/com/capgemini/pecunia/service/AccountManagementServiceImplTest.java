package com.capgemini.pecunia.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.pecunia.exception.AccountException;

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
	void testUpdateCustomerName() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCustomerContact() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCustomerAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateAccountId() {
		fail("Not yet implemented");
	}


	@Test
	@DisplayName("Null inputs here")
	void testAddAccountNull() {
		
		assertThrows(AccountException.class, ()-> { account.addAccount(null, null, null) ;});
		
	}
}


	
	
	



