package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressId() {
		return addressId;
	}

	public CustomerEntity(String name, String addressId, String aadhar, String pan, String contact, String gender,
			String dob) {
		super();
		this.name = name;
		this.addressId = addressId;
		this.aadhar = aadhar;
		this.pan = pan;
		this.contact = contact;
		this.gender = gender;
		this.dob = dob;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Column(name = "name")
	private String name;
	@Column(name = "address_id")
	private String addressId;
	@Column(name = "aadhar")
	private String aadhar;
	@Column(name = "pan")
	private String pan;
	@Column(name = "contact")
	private String contact;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private String dob;

}


