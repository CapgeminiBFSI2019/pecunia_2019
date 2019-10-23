package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class AccountEntity {

	public AccountEntity(String customerId, String branchId, String type, String status, String balance,
			String interest, String lastUpdated) {
		super();
		this.customerId = customerId;
		this.branchId = branchId;
		this.type = type;
		this.status = status;
		this.balance = balance;
		this.interest = interest;
		this.lastUpdated = lastUpdated;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Id
	@Column(name = "account_id")
	private int accountId;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "branch_id")
	private String branchId;
	@Column(name = "type")
	private String type;
	@Column(name = "status")
	private String status;
	@Column(name = "balance")
	private String balance;

	@Column(name = "interest")
	private String interest;

	@Column(name = "last_updated")
	private String lastUpdated;
}
