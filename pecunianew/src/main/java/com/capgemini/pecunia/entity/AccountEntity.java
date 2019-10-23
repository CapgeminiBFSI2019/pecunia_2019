package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
