package com.capgemini.pecunia.dto;

public class LoanDisbursal {

	private int loanDisbursalId;
	private int loanId;
	private String accountId;
	private double disbursedAmount;
	private double dueAmount;
	private double numberOfEmiToBePaid;

	public int getLoanDisbursalId() {
		return loanDisbursalId;
	}

	public void setLoanDisbursalId(int loanDisbursalId) {
		this.loanDisbursalId = loanDisbursalId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getDisbursedAmount() {
		return disbursedAmount;
	}

	public void setDisbursedAmount(double disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public double getNumberOfEmiToBePaid() {
		return numberOfEmiToBePaid;
	}

	public void setNumberOfEmiToBePaid(double numberOfEmiToBePaid) {
		this.numberOfEmiToBePaid = numberOfEmiToBePaid;
	}

	public LoanDisbursal(int loanDisbursalId, int loanId, String accountId, double disbursedAmount, double dueAmount,
			double numberOfEmiToBePaid) {
		super();
		this.loanDisbursalId = loanDisbursalId;
		this.loanId = loanId;
		this.accountId = accountId;
		this.disbursedAmount = disbursedAmount;
		this.dueAmount = dueAmount;
		this.numberOfEmiToBePaid = numberOfEmiToBePaid;
	}

	public LoanDisbursal() {

	}

	@Override
	public String toString() {
		return "loanDisbursalId=" + loanDisbursalId + ", loanId=" + loanId + ", accountId=" + accountId
				+ ", disbursedAmount=" + disbursedAmount + ", dueAmount=" + dueAmount + ", numberOfEmiToBePaid="
				+ numberOfEmiToBePaid + "";
	}

}
