package edu.vinaenter.models;


import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lands {

	private int lid;
	
	@NotEmpty(message = "Tên không được trống!")
	private String lname;
	
	@NotEmpty(message = "noi dung không được trống!")
	private String description;
	
	private Timestamp date_create;
	
	private String picture;
	
	@NotNull(message = "dien tich không được trống!")
	private Integer area;
	
	@NotEmpty(message = "vi tri không được trống!")
	private String address;
	
	private int count_views;
	
	private Categories cat;
	//private int cid;

	public Lands(String lname, String description, String picture, Integer area, String address, Categories cat) {
		super();
		this.lname = lname;
		this.description = description;
		this.picture = picture;
		this.area = area;
		this.address = address;
		this.cat = cat;
	}
	
	//private String cname;

	
	
}
