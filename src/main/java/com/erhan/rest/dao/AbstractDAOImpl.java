package com.erhan.rest.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

	private Class<T> entityClass;
	
	private Session currentSession;
	
	private Transaction currentTransaction;
	
	public AbstractDAOImpl(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	protected abstract SessionFactory getSessionFactory();
	
	public Session openSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	
	public Session openSessionWithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionWithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public void create(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public void remove(T entity) {
		getCurrentSession().remove(entity);
	}

	@Override
	public T findById(int id) {
		return getCurrentSession().find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		return getCurrentSession().createQuery(criteriaQuery).getResultList();
	}
}
