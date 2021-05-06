package edu.vinaenter.controllers.auth;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.constants.URLConstant;

@Controller
@RequestMapping(URLConstant.AUTH)
public class LoginController {
	
	@Resource
	MessageSource messegeSource; 
	
	@GetMapping(URLConstant.LOGIN)
	public String login() {
		return "auth.login";
	}
	
}
