package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.pecunia.exception.MyException;

class PassbookMaintenanceServiceImplTest {
PassbookMaintenanceServiceImpl pbm;
	
	@BeforeEach
	void setUp() throws Exception {
		pbm = new PassbookMaintenanceServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		pbm=null;
	}

	@Test
	@DisplayName("Input is null")
	void testPassbookMaintenanceNull() {
		assertThrows(Exception.class, ()-> {pbm.updatePassbook(null);});
	}
	
	@Test
	@DisplayName("Input is empty")
	void testPassbookMaintenanceEmpty() {
		assertThrows(Exception.class, ()-> {pbm.updatePassbook("");});
	}
	
	@Test
	@DisplayName("Input is not an integer")
	void testPassbookMaintenanceSpecialChar() {
		assertThrows(Exception.class, ()-> {pbm.updatePassbook("ab123567890^AVN");});
	}
	
	
	
}