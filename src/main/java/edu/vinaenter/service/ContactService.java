package edu.vinaenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.dao.ContactDAO;
import edu.vinaenter.models.Contact;

@Service
public class ContactService implements ICRUDService<Contact>{

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public List<Contact> getAll() {
		// TODO Auto-generated method stub
		return contactDAO.getAll();
	}

	@Override
	public List<Contact> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return contactDAO.getAll(offset, rowCount);
	}
	@Override
	public int update(Contact t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Contact t) {
		// TODO Auto-generated method stub
		return contactDAO.save(t);
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return contactDAO.del(id);
	}

	@Override
	public Contact findOne(Contact t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return contactDAO.numerOfItems();
	}
}
