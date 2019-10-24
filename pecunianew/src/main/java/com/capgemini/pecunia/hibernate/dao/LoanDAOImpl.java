package com.capgemini.pecunia.hibernate.dao;

import org.hibernate.Session;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.entity.LoanRequestEntity;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.HibernateUtil;

public class LoanDAOImpl implements LoanDAO {

	@Override
	public boolean addLoanDetails(Loan loan) throws PecuniaException, LoanException {
		// TODO Auto-generated method stub
		// return false;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			LoanRequestEntity loanRequestEntity = new LoanRequestEntity();
			loanRequestEntity.setAccountId(loan.getAccountId());
			loanRequestEntity.setAmount(loan.getAmount());
			loanRequestEntity.setCreditScore(loan.getCreditScore());
			loanRequestEntity.setEmi(loan.getEmi());
			loanRequestEntity.setRoi(loan.getRoi());
			loanRequestEntity.setTenure(loan.getTenure());
			loanRequestEntity.setType(loan.getType());
			loanRequestEntity.setStatus(loan.getLoanStatus());
			loanRequestEntity.setLoanId(loan.getLoanId());
			session.save(loanRequestEntity);
		} catch (Exception e) {
			throw new PecuniaException(ErrorConstants.LOAN_ADD_ERROR);

		}
		return false;

	}

}