package com.capgemini.pecunia.dto;

import java.util.Date;

public class Account {
	
	private String id;
	private String holderId;
	private String branchId;
	private String accountType;
	private String status;
	private double balance;
	private double interest;
	private Date lastUpdated;
	
	public Account()
	{
		
	}
	
	public Account(String id, String holderId, String branchId, String accountType, String status, double balance,
			double interest, Date lastUpdated) {
		super();
		this.id = id;
		this.holderId = holderId;
		this.branchId = branchId;
		this.accountType = accountType;
		this.status = status;
		this.balance = balance;
		this.interest = interest;
		this.lastUpdated = lastUpdated;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHolderId() {
		return holderId;
	}
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
