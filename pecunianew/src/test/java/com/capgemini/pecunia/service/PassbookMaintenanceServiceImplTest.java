package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import java.time.LocalDate;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.pecunia.exception.PassbookException;

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

	//
	@Test
	@DisplayName("Input is null")
	void testPassbookMaintenanceNull() {
		assertThrows(PassbookException.class, ()-> {pbm.updatePassbook(null);});
	}
	
	//
	@Test
	@DisplayName("Input is empty")
	void testPassbookMaintenanceEmpty() {
		assertThrows(PassbookException.class, ()-> {pbm.updatePassbook("");});
	}
	
	
	
	@Test
	@DisplayName("All inputs are null")
	
	void testAccountSummaryNull() {
		assertThrows(PassbookException.class, ()-> {pbm.accountSummary(null, null, null);});
	}
	
	@Test
	@DisplayName("Account ID is empty")
	void testAccountSummaryEmpty() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		LocalDate date1=LocalDate.parse(sDate1);
		LocalDate date2=LocalDate.parse(sDate2);
		assertThrows(PassbookException.class, ()-> {pbm.accountSummary("",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("Account ID is null")
	void testAccountSummaryNull1() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		LocalDate date1=LocalDate.parse(sDate1);
		LocalDate date2=LocalDate.parse(sDate2);
		assertThrows(PassbookException.class, ()-> {pbm.accountSummary("",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("Input for Account ID is invalid")
	void testAccountSummarySpecialChar() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		LocalDate date1=LocalDate.parse(sDate1);
		LocalDate date2=LocalDate.parse(sDate2);
		assertThrows(PassbookException.class, ()-> {pbm.accountSummary("1as*&Ak1234",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("End Date is before start date")
	void testAccountSummaryDateMismatch() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		LocalDate date1=LocalDate.parse(sDate1);
		LocalDate date2=LocalDate.parse(sDate2);
		assertThrows(PassbookException.class, ()-> {pbm.accountSummary("123456789101",date1 ,date2 );});
	}
	
	
	
}