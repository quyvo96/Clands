package edu.vinaenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.dao.UsersDAO;
import edu.vinaenter.models.Lands;
import edu.vinaenter.models.Users;

@Service
public class UsersService implements ICRUDService<Users>{

	@Autowired
	private UsersDAO usersDAO;
	
	@Override
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return usersDAO.getAll();
	}

	@Override
	public List<Users> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return usersDAO.getAll(offset, rowCount);
	}
	@Override
	public int update(Users t) {
		// TODO Auto-generated method stub
		return usersDAO.update(t);
	}

	@Override
	public int save(Users t) {
		// TODO Auto-generated method stub
		return usersDAO.save(t);
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return usersDAO.del(id);
	}

	@Override
	public Users findOne(Users t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByID(int id) {
		// TODO Auto-generated method stub
		return usersDAO.findByID(id);
	}


	@Override
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return usersDAO.numerOfItems();
	}
	
	public int updateEnabled(Users t) {
		return usersDAO.updateEnabled(t);
	}
	
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return usersDAO.findByName(name);
	}
	
	public List<Users> search(String sname, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return usersDAO.search(sname, offset, rowCount);
	}
	public int numerOfItemsSearch(String s) {

		
		return usersDAO.numerOfItemsSearch(s);
	}
}
