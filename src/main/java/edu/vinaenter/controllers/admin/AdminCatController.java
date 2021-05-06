package edu.vinaenter.controllers.admin;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Categories;
import edu.vinaenter.models.Users;
import edu.vinaenter.service.CategoriesService;
import edu.vinaenter.service.UsersService;

@Controller
@RequestMapping(URLConstant.URL_ADMIN_CAT)
public class AdminCatController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminCatController.class);
	
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping(URLConstant.INDEX)
	public String cat(Model model) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);

		return "admin.cat.index";
	}
	
	@GetMapping(URLConstant.ADD)
	public String add(Model model) {

		return "admin.cat.add";
		
	}
	@PostMapping(URLConstant.ADD)
	public String add(@Valid @ModelAttribute("cat") Categories cat,BindingResult rs,
			RedirectAttributes ra) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");

			return "admin.cat.add";
		}
		int countRecordInserted = categoriesService.save(cat);
		if(countRecordInserted>0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));	
			return "redirect:/admin/cat/index";
		}
		return "admin.cat.add";
	}
	@GetMapping(URLConstant.DEL)
	public String del(@RequestParam(required = false) int cid,RedirectAttributes ra) {

		int countRecordDEL = categoriesService.del(cid);
		if(countRecordDEL > 0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/cat/index";
		}
		return "redirect:/admin/cat/index";
	}
	
	@GetMapping(URLConstant.EDIT)
	public String edit(Model model,@RequestParam(required = false) int cid) {
		
		Categories cat;
		cat = categoriesService.findByID(cid);
		model.addAttribute("cat", cat);		
		return "admin.cat.edit";
		
	}
	
	@PostMapping(URLConstant.EDIT)
	public String edit(@Valid @ModelAttribute("cat") Categories cat,BindingResult rs,RedirectAttributes ra) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");

			return "redirect:/admin/cat/add";
		}
		int countRecordEdit = categoriesService.update(cat);
		if(countRecordEdit > 0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/cat/index";
		}
		return "redirect:/admin/cat/index";
		
	}
	

}
