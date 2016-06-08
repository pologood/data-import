package com.bj58.chr.data.handler;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.CVencryptCheck;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 上午10:49:09
 * @see
 * @since
 */
public interface Handler {

	public Status<String> doFullMatch(CVencryptCheck cVencryptCheck);

	public Status<String> doMobileMatch(CVencryptCheck cVencryptCheck);

	public Status<String> doEmailMatch(CVencryptCheck cVencryptCheck);

	public Status<String> doNotMatch(CVencryptCheck cVencryptCheck);

}
