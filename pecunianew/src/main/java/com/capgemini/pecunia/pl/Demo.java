package com.capgemini.pecunia.pl;

import java.sql.Connection;

import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class Demo {
	public static void main(String[] args) {
	
//		PropertyConfigurator.configure("resources//log4j.properties");
		try {
			Connection conn = DBConnection.getInstance().getConnection();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
