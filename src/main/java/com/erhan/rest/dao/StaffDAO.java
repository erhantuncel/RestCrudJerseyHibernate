package com.erhan.rest.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import com.erhan.rest.model.Department;
import com.erhan.rest.model.Staff;
import com.erhan.rest.util.HibernateUtil;

public class StaffDAO extends AbstractDAOImpl<Staff> {

	public StaffDAO() {
		super(Staff.class);
	}
	
	@Override
	protected SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}
	
	public List<Staff> findByFirtsName(String firstName) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("firstName"), firstName));
		return getCurrentSession().createQuery(cq).getResultList();
	}
	
	public List<Staff> findByLastName(String lastName) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("lastName"), lastName));
		return getCurrentSession().createQuery(cq).getResultList();
	}
	
	public List<Staff> findByPhone(String phone) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("phone"), phone));
		return getCurrentSession().createQuery(cq).getResultList();
	}
	
	public List<Staff> findByEmail(String email) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("email"), email));
		return getCurrentSession().createQuery(cq).getResultList();
	}
	
	public List<Staff> findByRegisteredTime(Date registeredTime) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("registeredTime"), registeredTime));
		return getCurrentSession().createQuery(cq).getResultList();
	}
	
	public List<Staff> findByDepartment(Department department) {
		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Staff> cq = cb.createQuery(Staff.class);
		Root<Staff> staff = cq.from(Staff.class);
		cq.select(staff);
		cq.where(cb.equal(staff.get("department"), department));
		return getCurrentSession().createQuery(cq).getResultList();
	}

}
