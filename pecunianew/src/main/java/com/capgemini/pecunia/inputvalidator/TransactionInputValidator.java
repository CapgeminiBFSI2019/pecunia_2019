package com.capgemini.pecunia.inputvalidator;

public class TransactionInputValidator {

	public boolean validateAccId(String accId) {
		boolean validator = false;
		if (accId != null && !accId.isEmpty() && accId.length() == 12) {
			if (accId.matches("[0-9]{14}")) {
				validator = true;
			}
		}
		return validator;
	}

	public boolean transactionAmountValidator(double amount) {
		boolean validator = false;
		if (amount > 0) {
			validator = true;
		}
		return validator;
	}

	public boolean chequeNumberValidator(int chequeNum) {
		boolean validator = false;
		if (Integer.toString(chequeNum).length() == 6) {
			if (Integer.toString(chequeNum).matches("[0-9]{6}")) {
				validator = true;
			}
		}
		return validator;
	}

	public boolean validateAccountName(String accountName) {
		boolean validator = false;
		String[] Nametmp = accountName.split("\\s+");
		if (accountName != null && !accountName.isEmpty()) {
			if (Nametmp[0].matches("[A-Za-z]+") && Nametmp[1].matches("[A-Za-z]+")) {
				validator = true;
			}
		}
		return validator;
	}

}
