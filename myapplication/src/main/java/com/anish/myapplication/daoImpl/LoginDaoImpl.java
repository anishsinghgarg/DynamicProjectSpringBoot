package com.anish.myapplication.daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anish.myapplication.dao.LoginDao;
import com.anish.myapplication.modal.LoginModal;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public LoginModal validateLoginUser(LoginModal loginModel) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		LoginModal _loginModel = new LoginModal();
		try {
			System.out.println(loginModel.getUserName() + "|" + loginModel.getPassword());
			// CriteriaBuilder builder = session.getCriteriaBuilder();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(LoginModal.class);

			Criterion username = Restrictions.eq("userName", loginModel.getUserName());
			Criterion password = Restrictions.eq("password", loginModel.getPassword());
			LogicalExpression andExp = Restrictions.and(username, password);
			criteria.add(andExp);
			_loginModel = (LoginModal) criteria.list().get(0);
		} catch (IndexOutOfBoundsException e) {
			_loginModel = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loginModel;
	}

	@Override
	public LoginModal addLoginUser(LoginModal loginModel) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(loginModel);
			session.flush();
			session.clear();
			session.getTransaction().commit();
			return loginModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
