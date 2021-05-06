package edu.vinaenter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

		public static Date convertStringToDate(String str) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateTL = null;
			dateTL = sdf.parse(str);
			return dateTL;
		}
}
