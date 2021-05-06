package edu.vinaenter.controllers.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Users;
import edu.vinaenter.service.CategoriesService;
import edu.vinaenter.service.LandsService;
import edu.vinaenter.service.UsersService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private LandsService landsService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UsersService usersService;
	

	
	@GetMapping(URLConstant.INDEX)
	public String index(Model model,HttpServletRequest request, Authentication authentication) {
		HttpSession session = request.getSession();
		Users userLogin = usersService.findByName(authentication.getName());
		session.setAttribute("userInfo", userLogin);
		int countCat = categoriesService.numerOfItems();
		int countLand = landsService.numerOfItems();
		int countUser = usersService.numerOfItems();
		model.addAttribute("countCat", countCat);
		model.addAttribute("countLand", countLand);
		model.addAttribute("countUser", countUser);
		model.addAttribute("userLogin", userLogin);
		return "admin.index";
	}
}
