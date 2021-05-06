package edu.vinaenter.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.GlobalConstant;
import edu.vinaenter.models.Contact;

@Repository
public class ContactDAO extends AbstractDAO<Contact>{

	@Override
	public List<Contact> getAll() {
		String sql = "SELECT * FROM vnecontact ORDER BY cid DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
	}
	@Override
	public List<Contact> getAll(int offset,int rowCount) {
		String sql = "SELECT * FROM vnecontact ORDER BY cid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), offset, rowCount);
	}
	@Override
	public int save(Contact t) {
		String sql = "INSERT INTO vnecontact (fullname,email,subject,content) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql,t.getFullname(),t.getEmail(),t.getSubject(),t.getContent());
	}
	@Override
	public int del(int id) {
		String sql = "DELETE FROM vnecontact WHERE cid = ?";
		return jdbcTemplate.update(sql, id);
	}
	@Override
	public Contact findByID(int id) {
		String sql = "SELECT * FROM vnecontact WHERE cid = ? ";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), new Object[] {id});
	}
	
	@Override
	public int update(Contact t) {
		String sql = "UPDATE vnecontact SET fullname = ?,email = ?,subject = ?,content = ?,  WHERE cid = ?";
		return jdbcTemplate.update(sql,t.getFullname(),t.getEmail(),t.getSubject(),t.getContent(), t.getCid());
	}

	@Override
	public int numerOfItems() {
		String sql = "SELECT COUNT(*) AS count FROM vnecontact";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
