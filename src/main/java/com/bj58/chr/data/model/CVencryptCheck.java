package com.bj58.chr.data.model;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.data.model.yccv.CvInfoBo;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午3:52:36
 * @see
 * @since
 */
public class CVencryptCheck {

	private final static Logger LOG = LoggerFactory.getLogger(CVencryptCheck.class);

	private CvIdCoId cvIdCoId;
	private Set<CVencrypt> mn;
	private CVAsk cvask;
	private CvInfoBo cvInfoBo;
	private CVencryptCheckEnum checkEnum;

	public CVencryptCheck(Set<CVencrypt> mn, CVAsk cvask) {
		this.mn = mn;
		this.cvask = cvask;
	}

	public CVencryptCheck(Set<CVencrypt> mn, CVAsk cvask, CvIdCoId cvIdCoId) {
		this.mn = mn;
		this.cvask = cvask;
		this.cvIdCoId = cvIdCoId;
	}

	public Set<CVencrypt> getMn() {
		return mn;
	}

	public void setMn(Set<CVencrypt> mn) {
		this.mn = mn;
	}

	public CVAsk getCvask() {
		return cvask;
	}

	public void setCvask(CVAsk cvask) {
		this.cvask = cvask;
	}

	public CvIdCoId getCvIdCoId() {
		return cvIdCoId;
	}

	public void setCvIdCoId(CvIdCoId cvIdCoId) {
		this.cvIdCoId = cvIdCoId;
	}

	public CvInfoBo getCvInfoBo() {
		return cvInfoBo;
	}

	public void setCvInfoBo(CvInfoBo cvInfoBo) {
		this.cvInfoBo = cvInfoBo;
	}

	/**
	 * @return
	 */
	public CVencryptCheckEnum isValidate(CVAsk cvask) {
		boolean nmm = false;
		boolean em = false;
		if (CollectionUtils.isNotEmpty(mn)) {
			nmm = true;
		}
		if (nmm && em) {
			checkEnum = CVencryptCheckEnum.FullMatch;
		} else if (nmm && !em) {
			checkEnum = CVencryptCheckEnum.MobileMatch;
		} else if (!nmm && em) {
			checkEnum = CVencryptCheckEnum.EmailMatch;
		} else {
			checkEnum = CVencryptCheckEnum.NotMatch;
		}

		LOG.info(cvask.getMndigest() + "," + mn + "," + nmm + "," + em + "," + checkEnum);
		return checkEnum;
	}

	/**
	 * @return
	 */
	public boolean isNew() {
		return cvIdCoId == null;
	}

	public CVencryptCheckEnum getCheckEnum() {
		return checkEnum;
	}

	@Override
	public String toString() {
		return "CVencryptCheck [cvIdCoId=" + cvIdCoId + ", mn=" + mn + ", cvask=" + cvask + ", cvInfoBo=" + cvInfoBo
				+ ", checkEnum=" + checkEnum + "]";
	}

}
