package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LoginServiceImplTest {
	
	LoginServiceImpl login;

	@BeforeEach
	void setUp() throws Exception {
		login = new LoginServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		login = null;
	}

	@Test
	@DisplayName("Input parameter object is null")
	void testValidateEmailNull() {
		assertThrows(NullPointerException.class, ()-> { login.validateEmail(null);});
	}


	
	
}
