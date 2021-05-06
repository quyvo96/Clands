package edu.vinaenter.controllers.admin;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.constants.GlobalConstant;
import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Contact;
import edu.vinaenter.models.Users;
import edu.vinaenter.service.ContactService;
import edu.vinaenter.service.UsersService;
import edu.vinaenter.util.PageUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN_CONTACT)
public class AdminContactController {
	
	
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping({URLConstant.INDEX,URLConstant.INDEX_PAGE})
	public String index(Model model,@PathVariable(required = false) Integer page) {
		List<Contact> listContact;
		if(page == null) {
			page = 1;
		}
		int numerOfItems = contactService.numerOfItems();
		int totalPage = PageUtil.getTotalPage(numerOfItems);
		//int offset = (numberPage-1)*GlobalConstant.NUMBER_PER_PAGE;
		int offset = PageUtil.getOffset(page);
		listContact = contactService.getAll(offset, GlobalConstant.NUMBER_PER_PAGE);
		model.addAttribute("listContact", listContact);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", page);
		return "admin.contact.index";
	}
	@GetMapping(URLConstant.DEL)
	public String del(@RequestParam int id,RedirectAttributes ra) {
		int del = contactService.del(id);
		if(del>0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/contact/index";
		}
		ra.addFlashAttribute("msg", messegeSource.getMessage("msg.error", null, Locale.getDefault()));
		return "redirect:/admin/contact/index";
	}

	

}
