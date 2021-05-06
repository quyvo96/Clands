package edu.vinaenter.models;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private int id;
	
	@NotEmpty(message = "Tên dang nhap không được trống!")
	private String username;
	
	@NotEmpty(message = "Tên day du không được trống!")
	private String fullname;
	
	@NotEmpty(message = "mat khau không được trống!")
	private String password;
	
	private int enabled;
	
	private Roles role;

	public Users(int id, int enabled) {
		super();
		this.id = id;
		this.enabled = enabled;
	}

	public Users(@NotEmpty(message = "Tên dang nhap không được trống!") String username,
			@NotEmpty(message = "Tên day du không được trống!") String fullname,
			@NotEmpty(message = "mat khau không được trống!") String password, int enabled, Roles role) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public Users(int id, @NotEmpty(message = "Tên dang nhap không được trống!") String username,
			@NotEmpty(message = "Tên day du không được trống!") String fullname, int enabled, Roles role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.enabled = enabled;
		this.role = role;
	}
	
	
	
}
