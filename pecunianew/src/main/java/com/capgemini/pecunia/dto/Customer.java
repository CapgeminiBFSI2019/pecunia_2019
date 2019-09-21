package com.capgemini.pecunia.dto;

import java.sql.Date;

public class Customer {
	
	private String id;
	private String name;
	private String addressId;
	private String aadhar;
	private String pan;
	private String contact;
	private String gender;
	private Date dob;
	
	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressId() {
		return addressId;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Customer(String id, String name, String addressId, String aadhar, String pan, String contact, String gender,
			Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.addressId = addressId;
		this.aadhar = aadhar;
		this.pan = pan;
		this.contact = contact;
		this.gender = gender;
		this.dob = dob;
	}

}
