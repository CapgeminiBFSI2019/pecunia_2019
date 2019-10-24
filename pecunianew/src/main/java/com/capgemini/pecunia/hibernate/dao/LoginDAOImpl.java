package com.capgemini.pecunia.hibernate.dao;

import com.capgemini.pecunia.dao.LoginQueryMapper;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.entity.AccountEntity;
import com.capgemini.pecunia.entity.LoginEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.DBConnection;
import com.capgemini.pecunia.util.HibernateUtil;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public String validateEmail(Login login) throws PecuniaException, LoginException {
		String secretKey = null;
		org.hibernate.Transaction transaction = null;

		String username = login.getUsername();
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createNamedQuery("LoginEntity.getsecret_keyByusername");
		query.setParameter("username", username);
		query.setMaxResults(1);
		LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
		if (loginEntity != null) {
			username = loginEntity.getUsername();
			System.out.println("jordi bhai");

		}
		transaction.commit();
		session.close();

		return secretKey;

	}

	@Override
	public String fetchPassword(Login login) throws PecuniaException, LoginException {
		org.hibernate.Transaction transaction = null;

		String password = null;

		String username = login.getPassword();
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createNamedQuery("LoginEntity.getpasswordByusername");
		query.setParameter("password", password);
		query.setMaxResults(1);
		LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
		if (loginEntity != null) {
			password = loginEntity.getPassword();
			}
			
			transaction.commit();
			session.close();
			

	
	return password;

	
}
}
