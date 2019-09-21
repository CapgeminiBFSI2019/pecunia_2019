package com.capgemini.pecunia.dto;

public class LoanDisbursal {
	
	String requestId;
	String customerId;
	double amount;
	String type;
	int tenure;
	double roi;
	String status;
	double emi;
	int creditScore;
	
	public LoanDisbursal(String requestId, String customerId, double amount, String type, int tenure, double roi,
			String status, double emi, int creditScore) {
		super();
		this.requestId = requestId;
		this.customerId = customerId;
		this.amount = amount;
		this.type = type;
		this.tenure = tenure;
		this.roi = roi;
		this.status = status;
		this.emi = emi;
		this.creditScore = creditScore;
	}
	
	
	public String getRequestId() {
		return requestId;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	

}
