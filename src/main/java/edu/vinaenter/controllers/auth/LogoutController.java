package edu.vinaenter.controllers.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.constants.URLConstant;

@Controller
@RequestMapping(URLConstant.AUTH)
public class LogoutController {
	
	@Resource
	MessageSource messegeSource; 
	
	@GetMapping(URLConstant.LOGOUT)
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return "auth.login";
	}
	
}
