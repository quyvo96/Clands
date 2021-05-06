package edu.vinaenter.controllers.Cland;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.constants.GlobalConstant;
import edu.vinaenter.constants.URLConstant;
import edu.vinaenter.models.Categories;
import edu.vinaenter.models.Contact;
import edu.vinaenter.models.Lands;
import edu.vinaenter.models.NumLandByCat;
import edu.vinaenter.service.CategoriesService;
import edu.vinaenter.service.ContactService;
import edu.vinaenter.service.LandsService;
import edu.vinaenter.util.PageUtil;
import edu.vinaenter.util.StringUtil;

@Controller
public class ClandController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClandController.class);
	
	@Resource
	MessageSource messegeSource; 
	
	@Autowired
	private LandsService landsService;
	
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ContactService contactService;
	
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(Model model) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		
		List<Lands> listLandsCount;
		listLandsCount = landsService.getAllMostCount();
		model.addAttribute("listLandsCount", listLandsCount);
		model.addAttribute("listNum", listNum);
        return "cland.error";
    }
    
    @GetMapping(URLConstant.ERROR_404)
    public String error(Model model) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		
		List<Lands> listLandsCount;
		listLandsCount = landsService.getAllMostCount();
		model.addAttribute("listLandsCount", listLandsCount);
		model.addAttribute("listNum", listNum);
        return "cland.error";
    }
	@GetMapping({URLConstant.HOME,URLConstant.HOME_PAGE})
	public String index(Model model, @PathVariable(required = false) Integer page, @RequestParam(required = false) String s) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		
		List<Lands> listLandsCount;
		listLandsCount = landsService.getAllMostCount();
		model.addAttribute("listLandsCount", listLandsCount);
		model.addAttribute("listNum", listNum);
		///////
		if(page == null) {
			page = 1;
		}
		int numerOfItems = 0;
		int totalPage = 0;
		List<Lands> listLands;
		int offset = PageUtil.getOffset(page);
		try {
			if(s!= null) {
				
					listLands = landsService.search(s,offset,GlobalConstant.NUMBER_PER_PAGE);
					numerOfItems = landsService.numerOfItemsSearch(s);
					totalPage = PageUtil.getTotalPage(numerOfItems);
					model.addAttribute("s", s);
					model.addAttribute("listLands", listLands);
					model.addAttribute("totalPage", totalPage);
					model.addAttribute("currentPage", page);
					return "cland.index";
				
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
		return "cland.index";
	}

	
	@GetMapping({URLConstant.URL_PUBLIC_CAT,URLConstant.URL_PUBLIC_CAT_PAGE})
	public String cat(Model model, @PathVariable(required = false) Integer page,
			@PathVariable(required = false) String nameCat) {
		int cid = 0;
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		
		List<Lands> listLandsCount;
		listLandsCount = landsService.getAllMostCount();
		model.addAttribute("listLandsCount", listLandsCount);
		model.addAttribute("listNum", listNum);
		

		for (Categories categories : listCat) {
			try {
				if(StringUtil.makeSlug(categories.getCname()).equals(nameCat)) {
					cid = 	categories.getCid();
				}
			} catch (Exception e) {
				System.out.println("error");
			}
		}
		if(cid == 0) throw new ResourceNotFoundException(nameCat);
		///////
		if(page == null) {
			page = 1;
		}
		Categories catByID = categoriesService.findByID(cid);
		model.addAttribute("catByID", catByID);
		int offset = PageUtil.getOffset(page);
		List<Lands> listLands;
		listLands = landsService.getByCat(cid,offset,GlobalConstant.NUMBER_PER_PAGE);
		int numerOfItems = landsService.numerOfItemsByCat(cid);
		int totalPage = PageUtil.getTotalPage(numerOfItems);
		model.addAttribute("listLands", listLands);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", page);
		//////
		return "cland.cat";
	}
	
	@GetMapping(URLConstant.URL_PUBLIC_CONTACT)
	public String contact(Model model) {
		List<Categories> listCat;
		listCat = categoriesService.getAll();
		model.addAttribute("listCat", listCat);
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		
		List<Lands> listLandsCount;
		listLandsCount = landsService.getAllMostCount();
		model.addAttribute("listLandsCount", listLandsCount);
		model.addAttribute("listNum", listNum);
		return "cland.contact";
		
	}
	@PostMapping(URLConstant.URL_PUBLIC_CONTACT)
	public String contact(@Valid @ModelAttribute("contact") Contact contact,BindingResult rs, RedirectAttributes ra) {
		if (rs.hasErrors()) {
			System.out.println("có lỗi data");
			return "cland.contact";
		}
		int save = contactService.save(contact);
		if(save > 0) {
			ra.addFlashAttribute("msg", messegeSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/contact";
		}
		ra.addFlashAttribute("msg", messegeSource.getMessage("msg.error", null, Locale.getDefault()));
		return "redirect:/contact?msg";
		
	}
	
	@GetMapping(URLConstant.URL_PUBLIC_SINGLE)
	public String single(Model model, @PathVariable(required = false) String nameCat, @PathVariable(required = false) String name,@PathVariable(required = false) Integer id) {
		
			List<Categories> listCat;
			listCat = categoriesService.getAll();
			model.addAttribute("listCat", listCat);
			List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
			for (Categories categories : listCat) {
				int count = landsService.numerOfItemsByCat(categories.getCid());
				NumLandByCat num = new NumLandByCat(categories.getCid(), count);
				listNum.add(num);
			}
			model.addAttribute("listNum", listNum);
			List<Lands> listLandsCount;
			listLandsCount = landsService.getAllMostCount();
			model.addAttribute("listLandsCount", listLandsCount);
			///////
			try {
			Lands land;
			land = landsService.findByID(id);
			model.addAttribute("land", land); 
			///tin liên quan 
			int idLQ = land.getCat().getCid();
			List<Lands> listLandsLQ;
			listLandsLQ = landsService.getByCat(idLQ, 0, 3);
			model.addAttribute("listLandsLQ", listLandsLQ); 
			Lands LandNext;
			LandNext = landsService.findNextByID(id);
			Lands LandBack;
			LandBack = landsService.findBackByID(id);
			int next = 0;
			int back = 0;
			if(LandNext==null) {
				next = 1;
			}
			if(LandBack==null) {
				back = 1;
			}
			model.addAttribute("next", next);
			model.addAttribute("back", back);
			return "cland.single";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			return "redirect:/404";
		}

	}
	@GetMapping(URLConstant.URL_NEXT_PAGE)
	public String nextPage(Model model,@PathVariable(required = false) Integer id) {
		Lands LandNext;
		LandNext = landsService.findNextByID(id);
		if(LandNext==null) {
			return "cland.single";
		}
		String nameCat = StringUtil.makeSlug(LandNext.getCat().getCname());
		String nameLand= StringUtil.makeSlug(LandNext.getLname());
		int lid = LandNext.getLid();
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		url = sbd.append(nameCat).append("/").append(nameLand).append("-").append(lid).toString();
		
		return "redirect:/" + url;
	}
	@GetMapping(URLConstant.URL_BACK_PAGE)
	public String backPage(Model model,@PathVariable(required = false) Integer id) {
		Lands LandBack;
		LandBack = landsService.findBackByID(id);
		if(LandBack==null) {
			return "cland.single";
		}
		String nameCat = StringUtil.makeSlug(LandBack.getCat().getCname());
		String nameLand= StringUtil.makeSlug(LandBack.getLname());
		int lid = LandBack.getLid();
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		url = sbd.append(nameCat).append("/").append(nameLand).append("-").append(lid).toString();
		
		return "redirect:/" + url;
	}
}
