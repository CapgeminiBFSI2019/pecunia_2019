package com.capgemini.pecunia.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.entity.LoginEntity;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;

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

import org.hibernate.Session;package com.capgemini.pecunia.hibernate.dao;

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
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createNamedQuery("LoginEntity.getsecret_keyByusername");
			query.setParameter("secretkey", secretKey);
			query.setMaxResults(1);
			LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
			if (loginEntity != null) {
				secretKey = loginEntity.getSecretKey();
				System.out.println("jordi bhai");

			} else {
				throw new PecuniaException(ErrorConstants.NO_SUCH_ACCOUNT);
			}

	
		
		transaction.commit();
		session.close();
	}
	catch (Exception e) {
		throw new PecuniaException(e.getMessage());
	}
		return secretKey;

	}

	@Override
	public String fetchPassword(Login login) throws PecuniaException, LoginException {
		String password = null;

		org.hibernate.Transaction transaction = null;
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createNamedQuery("LoginEntity.getpasswordByusername");
		query.setParameter("password", password);
		query.setMaxResults(1);
		LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
		if (loginEntity != null) {
			password = loginEntity.getPassword();
		}else {
			throw new PecuniaException(ErrorConstants.LOGIN_ERROR);
		}

		transaction.commit();
		session.close();
		}
		catch (Exception e) {
			throw new PecuniaException(e.getMessage());
		}
		return password;

	}
}

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

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createNamedQuery("LoginEntity.getsecret_keyByusername");
			query.setParameter("secretkey", secretKey);
			query.setMaxResults(1);
			LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
			if (loginEntity != null) {
				secretKey = loginEntity.getSecretKey();
				System.out.println("jordi bhai");

			} else {
				throw new PecuniaException(ErrorConstants.NO_SUCH_ACCOUNT);
			}

			transaction.commit();
			session.close();
		} catch (Exception e) {
			throw new PecuniaException(e.getMessage());
		}


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

		String password = null;

		org.hibernate.Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createNamedQuery("LoginEntity.getpasswordByusername");
			query.setParameter("password", password);
			query.setMaxResults(1);
			LoginEntity loginEntity = (LoginEntity) query.uniqueResult();
			if (loginEntity != null) {
				password = loginEntity.getPassword();
			} else {
				throw new PecuniaException(ErrorConstants.LOGIN_ERROR);
			}

			transaction.commit();
			session.close();
		} catch (Exception e) {
			throw new PecuniaException(e.getMessage());
		}
		return password;

	}

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
