package com.bj58.chr.data;

import com.google.common.base.Objects;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月28日 下午3:31:01
 * @see
 * @since
 */
public class HashTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// System.out.println(Objects.hashCode("0e5e4b6f-d036-4625-8b42-37e9a2329c62")
		// % 30);

		System.out.println(Objects.hashCode("3e1665bda7beef28624696597b8c020f") % 100);

	}

}
