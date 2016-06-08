package com.bj58.chr.data.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bj58.chr.common.ConstantUtils;
import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.handler.Handler;
import com.bj58.chr.data.handler.ICVContentHandler;
import com.bj58.chr.data.model.CVAsk;
import com.bj58.chr.data.model.CVContent;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.CVencryptCheckEnum;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.Need;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.IBlackListService;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.INeedService;
import com.bj58.chr.data.service.IStatService;
import com.bj58.chr.data.utils.JSONUtils;
import com.bj58.chr.data.utils.LogUtils;
import com.google.common.collect.Maps;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年3月30日 下午11:41:27
 * @see
 * @since
 */
@Controller
@RequestMapping(value = "/cv")
public class QDController extends SuperController {

	private final static Logger LOG = LoggerFactory.getLogger(QDController.class);

	@Resource
	private ICVencryptService cVencryptService;

	@Resource(name = "newHandler")
	private Handler newHandler;

	@Resource(name = "oldHandler")
	private Handler oldHandler;

	@Resource
	private ICVContentHandler cvContentHandler;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private INeedService needService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Resource
	private IStatService statService;

	@Resource
	private IContractService contractService;
	
	@Resource
	private IBlackListService blistService;

	@RequestMapping(value = "/ask", method = RequestMethod.GET)
	@ResponseBody
	public Status<String> askCV(@ModelAttribute CVAsk cvask, BindingResult result, HttpServletRequest request) {
		LogUtils.ask(cvask.toString());
		this.validate(cvask, result);
		if (result.hasErrors()) {
			return Status.newStatus(100, "传递参数错误:" + builderResult(result.getFieldErrors()));
		}
		if(blistService.isExist(cvask.getCoid())){
		    LOG.info(String.format("coid is %s 匹配到黑名单", cvask.getCoid()));
		    return Status.newStatus(300, "不需要这份简历");
		}
		Status<String> status = null;
		statService.askAll();
		CVencryptCheck cVencryptCheck = cVencryptService.getCVencrypt(cvask,
				cvIdCoIdService.findById(new CvIdCoId(cvask.getCoid())));
		if (cVencryptCheck.isNew()) {// 假如我们没有 分支代码
			statService.askNew();
			try {
				status = switchMethod(cVencryptCheck, newHandler);
			} catch (Exception e) {
				statService.askNewError();
				status = Status.newStatus(100, cvask.toString());
				LOG.error("ask error", e);
			}
		} else {// 假如我们已经有id
			statService.askOld();
			try {
				status = switchMethod(cVencryptCheck, oldHandler);
			} catch (Exception e) {
				statService.askOldError();
				status = Status.newStatus(100, cvask.toString());
				LOG.error("ask error", e);
			}
		}
		LogUtils.ask(status.toString());
		return status;
	}

	private Status<String> switchMethod(CVencryptCheck cVencryptCheck, Handler handler) {
		CVencryptCheckEnum cVencryptCheckEnum = cVencryptCheck.isValidate(cVencryptCheck.getCvask());
		Status<String> status = new Status<String>(300, "不需要这份简历");
		switch (cVencryptCheckEnum) {
		case FullMatch:
			status = handler.doFullMatch(cVencryptCheck);
			break;
		case MobileMatch:
			status = handler.doMobileMatch(cVencryptCheck);
			break;
		case EmailMatch:
			status = handler.doEmailMatch(cVencryptCheck);
			break;
		case NotMatch:
			status = handler.doNotMatch(cVencryptCheck);
			break;
		}
		if (status.getCode() >= 200 && status.getCode() < 300) {// 假如需要，如记录
			Need need = new Need(cVencryptCheck.getCvask().getCoid());
			needService.save(need);
		}

		return status;
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ResponseBody
	public Status<String> importCV(@ModelAttribute CVContent cvcontent, BindingResult result,
			HttpServletRequest request) {
		return cvImport(cvcontent, result, request, false);
	}

	@RequestMapping(value = "/fullImport", method = RequestMethod.POST)
	@ResponseBody
	public Status<String> fullImportCV(@ModelAttribute CVContent cvcontent, BindingResult result,
			HttpServletRequest request) {
		return cvImport(cvcontent, result, request, true);
	}

	private Status<String> cvImport(@ModelAttribute CVContent cvcontent, BindingResult result,
			HttpServletRequest request, boolean full) {
		LogUtils.content(cvcontent.toString());
		this.validate(cvcontent, result);
		Status<String> status = null;
		if (result.hasErrors()) {
			status = Status.newStatus(100, "传递参数错误:" + builderResult(result.getFieldErrors()));
		} else {
			CVModel cvModel = null;
			try {
				String debase64 = URLDecoder.decode(cvcontent.getContent(), ConstantUtils.CHARASET);
				debase64 = new String(Base64.decodeBase64(debase64.replaceAll("\\s", "+")), ConstantUtils.CHARASET);
				cvModel = JSONUtils.readValue(debase64, CVModel.class);
				if (cvModel != null) {
					CvInfoBo cvInfoBo = cvContentHandler.build(cvModel);
					if (cvInfoBo != null && cvInfoBo.getLiving() != null
							&& !StringUtils.isEmpty(cvInfoBo.getLiving().getCityId())) {
						try {
							cvInfoBoService.saveOrUpdate(cvInfoBo, cvcontent.getCoid());
							if (full) {
								needService.fulfilAll(cvcontent.getCoid());
							} else {
								needService.fulfil(cvcontent.getCoid());
							}
							status = Status.newStatus(200, "导入成功");
						} catch (Exception e) {
							statService.importjxError();
							LOG.error(String.format("handle error coid is %s", cvcontent.getCoid()), e);
							status = Status.newStatus(103, cvcontent.getCoid() + "服务器异常，请求稍后重试");
						}
					} else {
						statService.importjxError();
						status = Status.newStatus(102, cvcontent.getCoid() + "居住地域为空或只有省份");
					}
				} else {
					statService.importjxError();
					status = Status.newStatus(102, cvcontent.getCoid() + "简历解析失败");
				}
			} catch (Exception e1) {
				LOG.error(String.format("import error coid is %s", cvcontent.getCoid()), e1);
				statService.importjxError();
				status = Status.newStatus(101, cvcontent.getCoid() + "简历解析失败");
			}
		}
		LogUtils.content(status.toString());
		return status;
	}

	/**
	 * 
	 * 获取qd联系方式
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月25日
	 * @param cvid
	 * @return Map<String,String>
	 * 
	 */
	@RequestMapping(value = "/getQdContract", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getQdCvContract(@ModelAttribute CvIdCoId cvco) {
		Map<String, String> params = Maps.newHashMap();
		CVContract contract = contractService.findByCoId(cvco.getCoId());
		if (contract == null) {
			contract = asyncTaskService.updateContractImpl(cvco.getCoId());
		}
		if (contract != null) {
			params.put("email", contract.getData().getNewEmail());
			params.put("mobile", contract.getData().getNewMobile());
		}
		return params;
	}

}
