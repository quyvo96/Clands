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

@Repository
public class LandsDAO extends AbstractDAO<Lands>{

	@Override
	public List<Lands> getAll(int offset, int rowCount) {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid ORDER BY lid DESC"
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Lands>>() {
				List<Lands> lands = new ArrayList<Lands>();
					@Override
					public List<Lands> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands.add(new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname"))));
						}
						return lands;
					}
				}
				,offset,rowCount);
	}
	public List<Lands> getAllMostCount() {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid ORDER BY count_views DESC"
				+ " LIMIT 0,4";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Lands>>() {
				List<Lands> lands = new ArrayList<Lands>();
					@Override
					public List<Lands> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands.add(new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname"))));
						}
						return lands;
					}
				});
	}
	
	public List<Lands> getByCat(int id ,int offset, int rowCount) {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid AND l.cid = ? ORDER BY lid DESC"
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Lands>>() {
				List<Lands> lands = new ArrayList<Lands>();
					@Override
					public List<Lands> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands.add(new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname"))));
						}
						return lands;
					}
				}
				,id,offset,rowCount);
	}
	public List<Lands> search(String sname,int offset, int rowCount) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid AND lname LIKE ? ORDER BY lid DESC"
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Lands>>() {
				List<Lands> lands = new ArrayList<Lands>();
					@Override
					public List<Lands> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands.add(new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname"))));
						}
						return lands;
					}
				}
				,name,offset,rowCount);
	}
	public List<Lands> searchByIdAndName(int id, String sname,int offset, int rowCount) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid";
		if(id == 0) {
			sql += " AND l.cid != ?";
		}else {
			sql += " AND l.cid = ?";
		}
		sql += " AND lname LIKE ? ORDER BY lid DESC"
				+ " LIMIT ?,?";
		return jdbcTemplate.query(sql, 
				new ResultSetExtractor<List<Lands>>() {
				List<Lands> lands = new ArrayList<Lands>();
					@Override
					public List<Lands> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands.add(new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname"))));
						}
						return lands;
					}
				}
				,id,name,offset,rowCount);
	}
	@Override
	public int save(Lands t) {
		String sql = "INSERT INTO lands (lname,description, picture,cid,area, address ) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,t.getLname(),t.getDescription(),t.getPicture(),t.getCat().getCid(),t.getArea(), t.getAddress());
	}
	@Override
	public int update(Lands t) {
		String sql = "UPDATE lands SET lname = ? ,description= ?, picture =?,cid =?,area=?, address=? WHERE lid = ?";
		return jdbcTemplate.update(sql, t.getLname(),t.getDescription(),t.getPicture(),t.getCat().getCid(),t.getArea(), t.getAddress(),t.getLid());
	}
	@Override
	public Lands findByID(int id) {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid "
				+ "WHERE lid = ? ";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<Lands>() {
				Lands lands = null;
					@Override
					public Lands extractData(ResultSet rs) throws SQLException, DataAccessException {
						if(rs.next()) {
							lands=new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname")));
						}
						return lands;
					}
				}
				, id);
	}
	public Lands findNextByID(int id) {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid "
				+ "WHERE lid > ? ";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<Lands>() {
				Lands lands = null;
					@Override
					public Lands extractData(ResultSet rs) throws SQLException, DataAccessException {
						if(rs.next()) {
							lands=new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname")));
						}
						return lands;
					}
				}
				, id);
	}
	public Lands findBackByID(int id) {
		String sql = "SELECT "
				+ "lid,"
				+ "lname,"
				+ "description,"
				+ "date_create,"
				+ "picture,"
				+ "area,"
				+ "address,"
				+ "count_views,"
				+ "l.cid, cname"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid "
				+ "WHERE lid < ? ";
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<Lands>() {
				Lands lands = null;
					@Override
					public Lands extractData(ResultSet rs) throws SQLException, DataAccessException {
						while(rs.next()) {
							lands=new Lands(rs.getInt("lid"),
									rs.getString("lname"),
									rs.getString("description"),
									rs.getTimestamp("date_create"),
									rs.getString("picture"),
									rs.getInt("area"),
									rs.getString("address"),
									rs.getInt("count_views"),
									new Categories(rs.getInt("cid"), rs.getString("cname")));
						}
						return lands;
					}
				}
				, id);
	}
	@Override
	public int numerOfItems() {

		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int numerOfItemsByCat(int id) {

		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid AND l.cid = ? ";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}
	public int numerOfItemsSearch(String sname) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid AND lname LIKE ? ";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, name);
	}

	public int numerOfItemsSearchIdName(int id,String sname) {
		String name = "%"+sname+"%";
		String sql = "SELECT "
				+ "COUNT(*) AS count"
				+ " FROM lands AS l "
				+ "INNER JOIN categories AS c "
				+ "ON l.cid = c.cid";
		if(id == 0) {
			sql += " AND l.cid != ?";
		}else {
			sql += " AND l.cid = ?";
		}
				sql+= " AND lname LIKE ? ";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, id, name);
	}
	@Override
	public int del(int id) {
		String sql = "DELETE FROM lands WHERE lid = ?";
		return jdbcTemplate.update(sql, id);
	}
	

}
