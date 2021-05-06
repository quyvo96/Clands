package edu.vinaenter.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categories {

	private int cid;
	
	@NotEmpty(message = "Tên danh mục không được trống!")
	@Size(min = 5, max = 30)
	private String cname;


	public Categories(int cid) {
		super();
		this.cid = cid;
	}

	public Categories(int cid,
			@Size(min = 5, max = 30) @NotEmpty(message = "Tên danh mục không được trống!") String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}

	public Categories(@Size(min = 5, max = 30) @NotEmpty(message = "Tên danh mục không được trống!") String cname) {
		super();
		this.cname = cname;
	}
	
	
	
}
