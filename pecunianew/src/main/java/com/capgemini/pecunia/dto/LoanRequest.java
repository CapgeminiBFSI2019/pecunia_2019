package com.capgemini.pecunia.dto;


	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class LoanRequest {
		String requestId;
		String customerId;
		double amount;
		String type;
		int tenure;
		double roi;
		String loanStatus;
		double emi;
		int creditScore;

		
		
		public String getRequestId() {
			return requestId;
		}

		public String getCustomerId() {
			return customerId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double loanAmount) {
			this.amount = loanAmount;
		}

		public String getType() {
			return type;
		}

		public void setType(String loanType) {
			this.type = loanType;
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

		public void setRoi(double loanRoi) {
			this.roi = loanRoi;
		}

		public String getLoanStatus() {
			return loanStatus;
		}

		public void setLoanStatus(String loanStatus) {
			this.loanStatus = loanStatus;
		}

		public double getEmi() {
			return emi;
		}

		public void setEmi(double loanEmi) {
			this.emi = loanEmi;
		}

		public int getCreditScore() {
			return creditScore;
		}

		public void setCreditScore(int creditScore) {
			this.creditScore = creditScore;
		}

		public LoanRequest(String requestId, String customerId, double loanAmount, String loanType, int tenure,
				double loanRoi, String loanStatus, double loanEmi, int creditScore) {
			super();
			this.requestId = requestId;
			this.customerId = customerId;
			this.amount = loanAmount;
			this.type = loanType;
			this.tenure = tenure;
			this.roi = loanRoi;
			this.loanStatus = loanStatus;
			this.emi = loanEmi;
			this.creditScore = creditScore;
		}
	}

		