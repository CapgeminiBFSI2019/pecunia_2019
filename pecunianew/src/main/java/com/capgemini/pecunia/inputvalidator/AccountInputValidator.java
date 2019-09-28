package com.capgemini.pecunia.inputvalidator;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.capgemini.pecunia.util.Constants;

//import com.capgemini.pecunia.Values;

public class AccountInputValidator 
{
	public static boolean checkIfDigit(String value){


		boolean ifDigit=true;
		for(int check =0;check<value.length();check++){
			if(!Character.isDigit(value.charAt(check))) {
				ifDigit=false;
	            break;
			}
		}
		return ifDigit;
	}

	
	public static boolean checkIfAlphaNumeric(String value){
		int digit=0;
		int alpha=0;
		boolean isAlphaNumeric=false;
		for(int check =0;check<value.length();check++){
			if(Character.isDigit(value.charAt(check))) 
				digit++;
			else 
				alpha++;
		}
		if(digit!=0 && alpha!=0)
			isAlphaNumeric=true;
		if(Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",value)){
			isAlphaNumeric=false;
		}
		return isAlphaNumeric;
	}
	
	public static boolean checkIfSpecialCharacter(String value) {
		return Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",value);
	}
	
	public static boolean checkLength(int len, String value) {
		boolean length = false;
		if(value.length()==len)
			length =true;
		return length;
	}
	
	public static boolean dateValidator(String inputDate) {
		DateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_1);
		boolean isValidDate = false;
		sdf.setLenient(false);
		try {
			sdf.parse(inputDate);
			isValidDate = true;
		} catch (ParseException e) {
			isValidDate = false;
		}
		return isValidDate;
	}



	public boolean checkIfAlphaNumeric(String value){
		int digit=0;
		int alpha=0;
		boolean isAlphaNumeric=false;
		for(int check =0;check<value.length();check++){
			if(Character.isDigit(value.charAt(check))) 
				digit++;
			else if(!Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",value)){
				alpha++;
			}
		}
		if(digit!=0 && alpha!=0)
			isAlphaNumeric=true;
			
		return isAlphaNumeric;
	}
	public void checkIfSpecialCharacters(String value){
		int poop=0;
		
	}

//	if(Pattern.matches(".*[0-9]+.*", customerName) || Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",customerName ))
//	{
//		throw new InvalidParameterException("Your name is a digit?");
//	}		
//	if(customerGender!= "Male" || customerGender!= "Female" || customerGender!="Prefer not to say" )	
//	{
//		throw new InvalidParameterException("Invalid Input");
//	}
//	if(Pattern.matches(".*[0-9]+.*", addressCity) ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",addressCity))
//	{
//		throw new InvalidParameterException("Invalid Input");
//	}
//
//	if(Pattern.matches(".*[0-9]+.*", addressState) ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",addressState))
//	{
//		throw new InvalidParameterException("Invalid Input");
//	}
//	if(Pattern.matches(".*[0-9]+.*", addressCountry) ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",addressCountry))
//	{
//		throw new InvalidParameterException("Invalid Input");
//	}
//
//	if(accountType!= "Savings" || accountType!="FD" || accountType!="Current")
//	{
//
//		throw new InvalidParameterException("Invalid Input");
//	}
//
//	if(customerAadhar.length()!= 12 && Pattern.matches((".*[a-zA-Z]+.*"),customerAadhar) ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",customerAadhar))
//	{
//		throw new InvalidParameterException("Invalid Aadhar");
//		
//	}
//
//	if(customerPan.length()!=10 ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",customerPan))
//	{
//		throw new InvalidParameterException("Invalid PAN");
//		
//	}
//	if(customerContact.length()!=10 || Pattern.matches((".*[a-zA-Z]+.*"),customerContact) ||  Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",customerContact))
//	{
//		throw new InvalidParameterException("Invalid Contact Number");
//		
//	}
//
//	if(addressZipcode.length()!=6 || Pattern.matches((".*[a-zA-Z]+.*"),addressZipcode) || Pattern.matches(".*[!@#$%&*()_+=|<>?{}.\\[\\]~-]+.*",addressZipcode)) 
//	{
//		throw new InvalidParameterException("Invalid Zipcode");
//		
//	}
//	if(accountBalance<0)
//	{
//		throw new InvalidParameterException("Invalid Account Balance");
//		
//	}
//	if(accountInterest<0)
//	{
//		throw new InvalidParameterException("Invalid Account Interest");	
//		
//	}

	}

	
}



