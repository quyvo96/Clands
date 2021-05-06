package edu.vinaenter.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.vinaenter.models.Categories;
import edu.vinaenter.models.NumLandByCat;
import edu.vinaenter.service.LandsService;

public class NumLandCatUtil {
	
	@Autowired
	private static LandsService landsService;
	
	public static List<NumLandByCat> getNum(List<Categories> listCat) {
		List<NumLandByCat> listNum = new ArrayList<NumLandByCat>();
		for (Categories categories : listCat) {
			int count = landsService.numerOfItemsByCat(categories.getCid());
			NumLandByCat num = new NumLandByCat(categories.getCid(), count);
			listNum.add(num);
		}
		return listNum;
		
	}
}
