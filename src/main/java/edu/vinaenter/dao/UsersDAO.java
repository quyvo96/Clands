package edu.vinaenter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import edu.vinaenter.models.Categories;
import edu.vinaenter.models.Lands;
import edu.vinaenter.models.Roles;
import edu.vinaenter.models.Users;

@Repository
public class UsersDAO extends AbstractDAO<Users>{

	@Override
	public List<Users> getAll(int offset, int rowCount) {
		String sql = "SELECT "
				+ " u.id AS uid,"
				+ "username, "
				+ "fullname, "
				+ "password, "
				+ "enabled, "
				+ "role_id, name "
				+ "FROM users u "
				+ "INNER JOIN roles r "
				+ "ON role_id = r.id "
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Users>>() {
				List<Users> user = new ArrayList<Users>();
					@Override
					public List<Users> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							user.add(new Users(rs.getInt("uid"),
									rs.getString("username"),
									rs.getString("fullname"),
									rs.getInt("enabled"),
									new Roles(rs.getInt("role_id"), rs.getString("name"))));
						}
						return user;
					}
				}
				,offset,rowCount);
	}

	public List<Users> search(String sname,int offset, int rowCount) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ " u.id AS uid,"
				+ "username, "
				+ "fullname, "
				+ "password, "
				+ "enabled, "
				+ "role_id, name "
				+ "FROM users u "
				+ "INNER JOIN roles r "
				+ "ON role_id = r.id "
				+ " AND username LIKE ?"
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Users>>() {
				List<Users> user = new ArrayList<Users>();
					@Override
					public List<Users> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							user.add(new Users(rs.getInt("uid"),
									rs.getString("username"),
									rs.getString("fullname"),
									rs.getInt("enabled"),
									new Roles(rs.getInt("role_id"), rs.getString("name"))));
						}
						return user;
					}
				}
				,name,offset,rowCount);
	}
	public int numerOfItemsSearch(String sname) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM users AS u "
				+ "INNER JOIN roles AS r "
				+ "ON role_id = r.id AND username LIKE ? ";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, name);
	}
	
	@Override
	public int save(Users t) {
		String sql = "INSERT INTO users (username,fullname,password, role_id) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql,t.getUsername(),t.getFullname(),t.getPassword(), t.getRole().getId());
	}
	@Override
	public int del(int id) {
		String sql = "DELETE FROM users WHERE id = ? AND id !=1";
		return jdbcTemplate.update(sql, id);
	}
	@Override
	public Users findByID(int i) {
		String sql = "SELECT "
				+ " u.id AS uid,"
				+ "username, "
				+ "fullname, "
				+ "password, "
				+ "enabled, "
				+ "role_id, name "
				+ "FROM users u "
				+ "INNER JOIN roles r "
				+ "ON role_id = r.id "
				+ "AND u.id = ?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<Users>() {
				Users user = null;
					@Override
					public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
						if(rs.next()) {
							user = new Users(rs.getInt("uid"),
									rs.getString("username"),
									rs.getString("fullname"),
									rs.getInt("enabled"),
									new Roles(rs.getInt("role_id"), rs.getString("name")));
						}
						return user;
					}
				}
				,i);
	}
	
	@Override
	public int update(Users t) {
		String sql = "UPDATE users SET username = ?,fullname = ?, password = ?  WHERE id = ?";
		return jdbcTemplate.update(sql,t.getUsername(),t.getFullname(),t.getPassword(), t.getId());
	}
	
	public int updateEnabled(Users t) {
		String sql = "UPDATE users SET enabled = ?  WHERE id = ?";
		return jdbcTemplate.update(sql,t.getEnabled(), t.getId());
	}
	@Override
	public int numerOfItems() {
		String sql = "SELECT "
				+ "COUNT(*) AS count "
				+ " FROM users AS u"
				+ " INNER JOIN roles AS r"
				+ " ON role_id = r.id ";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Users findByName(String n) {
		String sql = "SELECT "
				+ " u.id AS uid,"
				+ "username, "
				+ "fullname, "
				+ "enabled, "
				+ "role_id, name "
				+ "FROM users u "
				+ "INNER JOIN roles r "
				+ "ON role_id = r.id "
				+ " WHERE username LIKE ?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<Users>() {
				Users user = null;
					@Override
					public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
						if(rs.next()) {
							user = new Users(rs.getInt("uid"),
									rs.getString("username"),
									rs.getString("fullname"),
									rs.getInt("enabled"),
									new Roles(rs.getInt("role_id"), rs.getString("name")));
						}
						return user;
					}
				}
				,n);
	}
}
