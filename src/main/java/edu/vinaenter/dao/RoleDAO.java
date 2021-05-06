package edu.vinaenter.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import edu.vinaenter.models.Roles;

@Repository
public class RoleDAO extends AbstractDAO<Roles>{

	@Override
	public List<Roles> getAll() {
		String sql = "SELECT * FROM roles ORDER BY id DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Roles.class));
	}

}
