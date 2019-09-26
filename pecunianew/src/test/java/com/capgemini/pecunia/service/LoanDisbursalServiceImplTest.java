package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoanDisbursalServiceImplTest {

	LoanDisbursalServiceImpl loanDisburse;
	
	@BeforeEach
	void setUp() throws Exception {
		loanDisburse=new LoanDisbursalServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		loanDisburse=null;
	}

	@Test
	@DisplayName("Test case passed for")
	void testApproveLoan() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLoanAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLoanStatus() {
		fail("Not yet implemented");
	}

}
