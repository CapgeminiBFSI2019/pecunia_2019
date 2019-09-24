package com.capgemini.pecunia.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		assertThrows(MyException.class, ()-> {pbm.updatePassbook(null);});
	}
	
	@Test
	@DisplayName("Input is empty")
	void testPassbookMaintenanceEmpty() {
		assertThrows(MyException.class, ()-> {pbm.updatePassbook("");});
	}
	
	@Test
	@DisplayName("Input is not an integer")
	void testPassbookMaintenanceSpecialChar() {
		assertThrows(MyException.class, ()-> {pbm.updatePassbook("ab123567890^AVN");});
	}
	
	@Test
	@DisplayName("All inputs are null")
	
	void testAccountSummaryNull() {
		assertThrows(MyException.class, ()-> {pbm.accountSummary(null, null, null);});
	}
	
	@Test
	@DisplayName("Account ID is empty")
	void testAccountSummaryEmpty() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
		assertThrows(MyException.class, ()-> {pbm.accountSummary("",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("Account ID is null")
	void testAccountSummaryNull1() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
		assertThrows(MyException.class, ()-> {pbm.accountSummary("",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("Input for Account ID is invalid")
	void testAccountSummarySpecialChar() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
		assertThrows(MyException.class, ()-> {pbm.accountSummary("1as*&Ak1234",date1 ,date2 );});
	}
	
	@Test
	@DisplayName("End Date is before start date")
	void testAccountSummaryDateMismatch() throws ParseException {
		String sDate1="2012-10-10";
		String sDate2="2013-10-10";
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
		assertThrows(MyException.class, ()-> {pbm.accountSummary("123456789101",date2 ,date1 );});
	}
	
	
	
}