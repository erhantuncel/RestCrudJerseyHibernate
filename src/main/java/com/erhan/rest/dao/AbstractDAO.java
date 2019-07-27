package com.erhan.rest.dao;

import java.util.List;

public interface AbstractDAO<T> {

	public void create(T entity);
	public void update(T entity);
	public void remove(T entity);
	public T findById(int id);
	public List<T> findAll();
}
