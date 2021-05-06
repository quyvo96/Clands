package edu.vinaenter.controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.constants.GlobalConstant;
import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Categories;
import edu.vinaenter.models.Lands;
import edu.vinaenter.models.Roles;
import edu.vinaenter.models.Users;
import edu.vinaenter.service.RoleService;
import edu.vinaenter.service.UsersService;
import edu.vinaenter.util.PageUtil;

@Controller
@RequestMapping(URLConstant.URL_ADMIN_USER)
public class AdminUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({URLConstant.INDEX,URLConstant.INDEX_PAGE})
	public String index(Model model, @PathVariable(required = false) Integer page,Authentication authentication, @RequestParam(required = false) String s) {
		Users userLogin = usersService.findByName(authentication.getName());
		///////
		if(page == null) {
			page = 1;
		}
		int numerOfItems = 0;
		int totalPage = 0;
		List<Users> listUser;
		int offset = PageUtil.getOffset(page);
		try {
			if(s!= null) {
					listUser = usersService.search(s,offset,GlobalConstant.NUMBER_PER_PAGE);
					numerOfItems = usersService.numerOfItemsSearch(s);
					totalPage = PageUtil.getTotalPage(numerOfItems);
					model.addAttribute("s", s);
					model.addAttribute("listUser", listUser);
					model.addAttribute("totalPage", totalPage);
					model.addAttribute("currentPage", page);
					return "admin.user.index";
				
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		listUser = usersService.getAll(offset,GlobalConstant.NUMBER_PER_PAGE);
		numerOfItems = usersService.numerOfItems();
		totalPage = PageUtil.getTotalPage(numerOfItems);
		model.addAttribute("listUser", listUser);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", page);

		model.addAttribute("userLogin", userLogin);
		return "admin.user.index";
	}
	
	@PostMapping(URLConstant.CHECK)
	public void index(@RequestParam(required = false) Integer idCheck,@RequestParam(required = false) Integer idUser,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		System.out.println(idCheck);
		if(idUser!= 1) {
			if(idCheck == 0) {
				idCheck = 1;
			}else {
				idCheck = 0;
			}
			Users user = new Users(idUser,idCheck);
			
			int updateEnabled = usersService.updateEnabled(user);
			
			if(updateEnabled > 0) {
				
				if(idCheck == 1) {
					out.print("Đã kích hoạt");
				}else {
					out.print("Chưa kích hoạt");
					
				}
			}

		}

	}
	@PostMapping(URLConstant.CHECK_NAME)
	public void index(@RequestParam(required = false) String nameCheck,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		if(nameCheck !=null) {
			Users userCheck = usersService.findByName(nameCheck);
			if(userCheck != null) {
				
				out.print("0");
			}else {
				out.print("1");
			}
			
		}


	}
	@GetMapping(URLConstant.ADD)
	public String add(Model model,Authentication authentication) {
		List<Roles> listRole = roleService.getAll();
		model.addAttribute("listRole", listRole);
		Users userLogin = usersService.findByName(authentication.getName());
		model.addAttribute("userLogin", userLogin);
		return "admin.user.add";
		
	}
	@PostMapping(URLConstant.ADD)
	public String add(Model model,@Valid  @ModelAttribute("user") Users user,BindingResult rs,
			RedirectAttributes ra) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");
			List<Roles> listRole = roleService.getAll();
			model.addAttribute("listRole", listRole);
			return "admin.user.add";
		}
		Users userCheck = usersService.findByName(user.getUsername());
		if(userCheck != null) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.error", null, Locale.getDefault()));
			return "redirect:/admin/user/add";
		}
		String pass = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(pass));
		int countRecordInserted = usersService.save(user);
		if(countRecordInserted>0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));	
			return "redirect:/admin/user/index";
		}
		return "admin.user.add";
	}
	@GetMapping(URLConstant.DEL)
	public String del(@RequestParam(required = false) int id,RedirectAttributes ra,Authentication authentication) {
		Users userLogin = usersService.findByName(authentication.getName());
		if(userLogin.getId() == 1) {
		int countRecordDEL = usersService.del(id);
		if(countRecordDEL > 0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/user/index";
		}
		}
		return "redirect:/admin/user/index";
	}
	
	@GetMapping(URLConstant.EDIT)
	public String edit(Model model,@RequestParam(required = false) int id,Authentication authentication) {
		Users userLogin = usersService.findByName(authentication.getName());
		if(userLogin.getId() == id) {
			Users user;
			user = usersService.findByID(id);
			model.addAttribute("user", user);	
			
			model.addAttribute("userLogin", userLogin);
			return "admin.user.edit";
		}

		return "redirect:/admin/user/index";
		
	}
	
	@PostMapping(URLConstant.EDIT)
	public String edit(Model model,@Valid  @ModelAttribute("user") Users user,BindingResult rs,RedirectAttributes ra ,Authentication authentication) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");
			List<Roles> listRole = roleService.getAll();
			model.addAttribute("listRole", listRole);
			return "admin.user.edit";
		}
		Users userLogin = usersService.findByName(authentication.getName());
		if(userLogin.getId() == user.getId()) {
			String pass = user.getPassword();
			user.setPassword(bCryptPasswordEncoder.encode(pass));
			int countRecordEdit = usersService.update(user);
			if(countRecordEdit > 0) {
				ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
				return "redirect:/admin/user/index";
			}
			return "redirect:/admin/user/index";
		}
		ra.addFlashAttribute("msg", messegeSource.getMessage("msg.error", null, Locale.getDefault()));
		return "redirect:/admin/user/index";
		
	}
	

}
