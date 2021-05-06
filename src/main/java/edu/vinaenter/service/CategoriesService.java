package edu.vinaenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.dao.CategoriesDAO;
import edu.vinaenter.models.Categories;

@Service
public class CategoriesService implements ICRUDService<Categories>{

	@Autowired
	private CategoriesDAO categoriesDAO;
	
	@Override
	public List<Categories> getAll() {
		// TODO Auto-generated method stub
		return categoriesDAO.getAll();
	}

	@Override
	public List<Categories> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Categories t) {
		// TODO Auto-generated method stub
		return categoriesDAO.update(t);
	}

	@Override
	public int save(Categories t) {
		// TODO Auto-generated method stub
		return categoriesDAO.save(t);
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return categoriesDAO.del(id);
	}

	@Override
	public Categories findOne(Categories t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categories findByID(int id) {
		// TODO Auto-generated method stub
		return categoriesDAO.findByID(id);
	}

	@Override
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return categoriesDAO.numerOfItems();
	}

}
