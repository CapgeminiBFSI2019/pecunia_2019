package com.capgemini.pecunia.dto;

import java.time.*;

public class Transaction {
	private String id;
	private String accountId;
	private String type;
	private Double amount;
	private String option;
	private LocalDate transDate;
	private int chequeId;
	private String transFrom;
	private String transTo;
	private Double closingBalance;

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}

	public int getChequeId() {
		return chequeId;
	}

	public void setChequeId(int chequeId) {
		this.chequeId = chequeId;
	}

	public String getTransFrom() {
		return transFrom;
	}

	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}

	public String getTransTo() {
		return transTo;
	}

	public void setTransTo(String transTo) {
		this.transTo = transTo;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Transaction(String id, String accountId, String type, Double amount, String option, LocalDate transDate,
			int chequeId, String transFrom, String transTo, Double closingBalance) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.option = option;
		this.transDate = transDate;
		this.chequeId = chequeId;
		this.transFrom = transFrom;
		this.transTo = transTo;
		this.closingBalance = closingBalance;
	}

	public Transaction() {

	}
}
