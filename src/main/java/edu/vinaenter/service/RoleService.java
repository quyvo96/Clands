package edu.vinaenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.dao.RoleDAO;
import edu.vinaenter.models.Roles;

@Service
public class RoleService implements ICRUDService<Roles>{

	@Autowired
	private RoleDAO rolesDAO;
	
	@Override
	public List<Roles> getAll() {
		// TODO Auto-generated method stub
		return rolesDAO.getAll();
	}

	@Override
	public List<Roles> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Roles t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Roles t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Roles findOne(Roles t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Roles findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return 0;
	}

}
