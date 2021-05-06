package edu.vinaenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDAO<T> {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public List<T> getAll(int offset,int rowCount) {
		// TODO Auto-generated method stub
		return null;
	} 
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int save(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int del(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	public T findOne(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	public T findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
