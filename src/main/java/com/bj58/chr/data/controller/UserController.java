package com.bj58.chr.data.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.Status;
import com.bj58.chr.common.web.page.PageRequest;
import com.bj58.chr.data.handler.NewHandler;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvIdCoIdService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月12日 下午3:24:07
 * @see
 * @since
 */
@Controller
@RequestMapping(value = "/user/login")
public class UserController extends SuperController {

	private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IContractService contractService;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private NewHandler newHandler;

	@RequestMapping(value = "/uptime", method = RequestMethod.GET)
	@ResponseBody
	public Status<String> uptime(HttpServletRequest request) {
		String uid = WebUtils.findString(request, "uid");
		String status = WebUtils.findString(request, "status");
		String createTime = WebUtils.findString(request, "createTime");
		try {
			contractService.saveOrUpdateLoginTime(uid,status,createTime);
			LOG.info(String.format("use login: uid is %s and status is %s", uid,status));
		} catch (Exception e) {
			LOG.error("error", e);
			return Status.fail("错误");
		}
		return Status.success("成功");
	}

	@RequestMapping(value = "/flush", method = RequestMethod.GET)
	@ResponseBody
	public Status<String> flush(HttpServletRequest request) {
		boolean flag = true;
		int page = 0;
		while (flag) {
			page++;
			List<CVContract> contractList = contractService.list(new PageRequest(page, 1000));
			if (contractList == null) {
				flag = false;
			} else {
				for (CVContract contract : contractList) {
					if (StringUtils.isEmpty(contract.getUid())) {
						String coid = contract.getCoid();
						LOG.info(coid);
						CvIdCoId cvidCoid = cvIdCoIdService.findById(new CvIdCoId(coid));
						if (cvidCoid != null) {
							String cvid = cvidCoid.getCvId();
							LOG.info(cvid);
							CvInfoBo cvInfoBo = newHandler.findOne(cvid);
							if (cvInfoBo != null) {
								contract.setUid(cvInfoBo.getUid());
								LOG.info(cvInfoBo.getUid());
								contractService.updateUid(contract.getCoid(), contract.getUid());
							}
						}
					}
				}
			}
		}
		return Status.success("成功");
	}

}
