package com.bj58.chr.data;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月5日 下午11:19:11
 * @see
 * @since
 */
public class DateTest {
	private final static long D30 = 60 * 60 * 24 * 7;

	public static void main(String[] args) {

		System.out.println(DateFormatUtils.format(1463493532000l, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateFormatUtils.format(1461934548000l, "yyyy-MM-dd HH:mm:ss"));

		// System.out.println(1462916196 - 1461985021);
		// System.out.println(D30);
		// try {
		//
		// System.out.println(QDStringUtils.getCvTime(DateUtils.parseDate("2016-05-07",
		// "yyyy-MM-dd").getTime()));
		//
		// System.out.println(QDStringUtils.getCvTime(DateUtils.parseDate("2016-05-08",
		// "yyyy-MM-dd").getTime()));
		//
		// //
		// db.cvInfoBo.count({'lRefTime':{'$gte':1462550400,'$lt':1462636800}});
		//
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }

	}

}
