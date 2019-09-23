package com.capgemini.pecunia.dto;

public class Employee {
	
	private String id;
	private String name;
	private String branchId;
	private String designation;
	private String username;
	private String password;
	private String salt;
	
	public Employee(String id, String name, String branchId, String designation, String username, String password,
			String salt) {
		super();
		this.id = id;
		this.name = name;
		this.branchId = branchId;
		this.designation = designation;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public String getId() {
		return id;
	}



	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBranchId() {
		return branchId;
	}



	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getUsername() {
		return username;
	}



	
	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}

}
