package com.erhan.rest.service;

import java.util.List;

public interface GenericService<T> {
	
	public void create(T entity);
	public void update(T entity);
	public void remove(T entity);
	public T findById(Integer id);
	public List<T> findAll();
}
