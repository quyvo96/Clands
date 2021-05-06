package edu.vinaenter.controllers.error;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.constants.URLConstant;

@Controller
@RequestMapping(URLConstant.ERROR)
public class ErrorController {
	
	@Resource
	MessageSource messegeSource; 
	
	@GetMapping(URLConstant.ERROR_403)
	public String login() {
		return "error.403";
	}
	
	
	

}
