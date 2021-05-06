package edu.vinaenter.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import edu.vinaenter.models.Categories;

@Repository
public class CategoriesDAO extends AbstractDAO<Categories>{

	@Override
	public List<Categories> getAll() {
		String sql = "SELECT * FROM categories ORDER BY cid DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categories.class));
	}
	@Override
	public int save(Categories t) {
		String sql = "INSERT INTO categories (cname) VALUES (?)";
		return jdbcTemplate.update(sql,t.getCname());
	}
	@Override
	public int del(int id) {
		String sql = "DELETE FROM categories WHERE cid = ?";
		return jdbcTemplate.update(sql, id);
	}
	@Override
	public Categories findByID(int id) {
		String sql = "SELECT * FROM categories WHERE cid = ? ";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Categories.class), new Object[] {id});
	}
	
	@Override
	public int update(Categories t) {
		String sql = "UPDATE categories SET cname = ? WHERE cid = ?";
		return jdbcTemplate.update(sql, t.getCname(), t.getCid());
	}

	@Override
	public int numerOfItems() {
		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM categories";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
