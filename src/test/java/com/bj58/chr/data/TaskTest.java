package com.bj58.chr.data;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.CV.contract.CvBasic;
import com.bj58.chr.CV.contract.CvUltimate;
import com.bj58.chr.CV.service.ICVService;
import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.esb.CvOperTypeEnum;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.esb.message.CvPostMessage;
import com.bj58.chr.data.model.Rule;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.IRuleHandlerService;
import com.bj58.chr.data.service.IRuleService;
import com.bj58.spat.scf.client.proxy.builder.ProxyFactory;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午9:38:38
 * @see
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class TaskTest {

  private ICVService cvService = ProxyFactory.create(ICVService.class,
  "tcp://cvservice/CVServiceImpl");
  
	@Resource
	private IRuleHandlerService ruleHandlerService;

	@Resource
	private IRuleService ruleService;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Test
	public void find() {
		Rule rule = ruleService.findById(1);
		Status<String> status = ruleHandlerService.handle(rule);
		System.out.println(status);
	}

	@Test
	public void updateContract() {
		asyncTaskService.updateContractImpl("00b3206d-261a-42ca-a7e4-948c0d795411");
	}
	
	
	@Test
	public void sendCvMsg() throws Exception{
	    CvBasic basic = cvService.getBasicByid("57234eb4e4b027bcad4e7c9c");
	    System.out.println(basic.getSensitive().getMobile()+","+basic.getSensitive().getEmail());
        }
	
}
