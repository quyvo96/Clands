package edu.vinaenter.controllers.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.constants.GlobalConstant;
import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Categories;
import edu.vinaenter.models.Lands;
import edu.vinaenter.models.Users;
import edu.vinaenter.service.CategoriesService;
import edu.vinaenter.service.LandsService;
import edu.vinaenter.service.UsersService;
import edu.vinaenter.util.FileUtil;
import edu.vinaenter.util.PageUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN_LAND)
public class AdminLandController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminCatController.class);
	
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private LandsService landsService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping({URLConstant.INDEX,URLConstant.INDEX_PAGE})
	public String index(Model model, @PathVariable(required = false) Integer page,
			@RequestParam(required = false) String s, 
			@RequestParam(required = false) Integer i) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		///////
		if(page == null) {
			page = 1;
		}
		if(i == null) {
			i = 0;
		}
		int numerOfItems = 0;
		int totalPage = 0;
		List<Lands> listLands;
		int offset = PageUtil.getOffset(page);
		try {
			if(s!= null || i != 0) {
				
					listLands = landsService.searchByIdAndName(i,s,offset,GlobalConstant.NUMBER_PER_PAGE);
					numerOfItems = landsService.numerOfItemsSearchIdName(i,s);
					totalPage = PageUtil.getTotalPage(numerOfItems);
					model.addAttribute("s", s);
					model.addAttribute("i", i);
					model.addAttribute("listLands", listLands);
					model.addAttribute("totalPage", totalPage);
					model.addAttribute("currentPage", page);
					return "admin.land.index";
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		listLands = landsService.getAll(offset,GlobalConstant.NUMBER_PER_PAGE);
		numerOfItems = landsService.numerOfItems();
		totalPage = PageUtil.getTotalPage(numerOfItems);
		model.addAttribute("listLands", listLands);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", page);
		//////
		return "admin.land.index";

	}
	
	@GetMapping(URLConstant.ADD)
	public String add(Model model) {

		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		return "admin.land.add";
		
	}
	@PostMapping(URLConstant.ADD)
	public String add(Model model, @Valid @ModelAttribute("land") Lands land,BindingResult rs,
			@RequestParam("file") MultipartFile file, RedirectAttributes ra,
			HttpServletRequest request) {
		
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");
			List<Categories> listCat;
			listCat = categoriesService.getAll();
			model.addAttribute("listCat", listCat);
			return "admin.land.add";
		}
		String fileName = FileUtil.upload(file,request);
		land.setPicture(fileName);
		
		int save = landsService.save(land);

		return "redirect:/admin/land/index";
		
	}
	
	@GetMapping({URLConstant.EDIT,URLConstant.EDIT_ID})
	public String edit(Model model,@PathVariable(required = false) Integer id) {
		if(id == null) {
			return "redirect:/admin/land/index";
		}
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		Lands land = landsService.findByID(id);
		model.addAttribute("land", land);	
		model.addAttribute("listCat", listCat);
		return "admin.land.edit";
		
	}
	
	@PostMapping(URLConstant.EDIT)
	public String edit(Model model,@Valid @ModelAttribute("land") Lands land ,BindingResult rs ,
			@RequestParam("file") MultipartFile file, RedirectAttributes ra,
			HttpServletRequest request) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");
			List<Categories> listCat;
			listCat = categoriesService.getAll();
			model.addAttribute("listCat", listCat);
			return "admin.land.edit";
		}
		Lands landOld = landsService.findByID(land.getLid());
		String pictureOld = landOld.getPicture();
		String fileName = FileUtil.reUpload(file,pictureOld,request);
		land.setPicture(fileName);
		
		int update = landsService.update(land);

		return "redirect:/admin/land/index";
		
	}
}
