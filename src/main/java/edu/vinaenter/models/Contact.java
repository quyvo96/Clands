package edu.vinaenter.models;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	private int cid;
	
	@NotEmpty(message = "Tên không được trống!")
	private String fullname;
	
	@NotEmpty(message = "email không được trống!")
	private String email;
	
	private String subject;
	
	private String content;
}
