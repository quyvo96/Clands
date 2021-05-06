package edu.vinaenter.util;

import edu.vinaenter.constants.GlobalConstant;

public class PageUtil {

	public static int getOffset(Integer page) {
		return (page-1)*GlobalConstant.NUMBER_PER_PAGE;
	}
	public static int getTotalPage(int numerOfItems) {
		

		return (int)Math.ceil((float)((float)numerOfItems/(float)GlobalConstant.NUMBER_PER_PAGE));
	}
}
