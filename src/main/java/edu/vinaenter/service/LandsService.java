package edu.vinaenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.dao.LandsDAO;
import edu.vinaenter.models.Lands;

@Service
public class LandsService implements ICRUDService<Lands>{

	@Autowired
	private LandsDAO landsDAO;
	
	@Override
	public List<Lands> getAll() {
		// TODO Auto-generated method stub
		return landsDAO.getAll();
	}
	@Override
	public List<Lands> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return landsDAO.getAll(offset, rowCount);
	}

	@Override
	public int update(Lands t) {
		// TODO Auto-generated method stub
		return landsDAO.update(t);
	}

	@Override
	public int save(Lands t) {
		// TODO Auto-generated method stub
		return landsDAO.save(t);
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return landsDAO.del(id);
	}

	@Override
	public Lands findOne(Lands t) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Lands findByID(int id) {
		// TODO Auto-generated method stub
		return landsDAO.findByID(id);
	}
	public Lands findNextByID(int id) {
		// TODO Auto-generated method stub
		return landsDAO.findNextByID(id);
	}
	public Lands findBackByID(int id) {
		// TODO Auto-generated method stub
		return landsDAO.findBackByID(id);
	}
	@Override
	public int numerOfItems() {
		// TODO Auto-generated method stub
		return landsDAO.numerOfItems();
	}
	public List<Lands> getByCat(int id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return landsDAO.getByCat(id, offset, rowCount);
	}
	
	public List<Lands> search(String sname, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return landsDAO.search(sname, offset, rowCount);
	}
	public List<Lands> searchByIdAndName(int id, String sname, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return landsDAO.searchByIdAndName(id,sname, offset, rowCount);
	}
	public int numerOfItemsByCat(int id) {

		
		return landsDAO.numerOfItemsByCat(id);
	}
	public int numerOfItemsSearch(String s) {

		
		return landsDAO.numerOfItemsSearch(s);
	}
	public int numerOfItemsSearchIdName(int i,String s) {

		
		return landsDAO.numerOfItemsSearchIdName(i,s);
	}
	
	public List<Lands> getAllMostCount() {
		// TODO Auto-generated method stub
		return landsDAO.getAllMostCount();
	}
}
