package com.bj58.chr.data.handler;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:48:52
 * @see
 * @since
 */
public interface ICVContentHandler {

	/**
	 * @param cvContent
	 * @return
	 */
	<T,S> S build(T cvModel);

}
