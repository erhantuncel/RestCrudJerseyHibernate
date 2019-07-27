package com.erhan.rest.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import com.erhan.rest.model.Department;
import com.erhan.rest.util.HibernateUtil;

public class DepartmentDAO extends AbstractDAOImpl<Department>{

	public DepartmentDAO() {
		super(Department.class);
	}
	
	@Override
	protected SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}
	
	public Department findByDepartmentName(String name) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Department> cq = cb.createQuery(Department.class);
		Root<Department> department = cq.from(Department.class);
		cq.select(department);
		cq.where(cb.equal(department.get("departmentName"), name));
		return getCurrentSession().createQuery(cq).uniqueResult();
	}
}
