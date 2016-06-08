package com.bj58.chr.data;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.service.IContractService;

/**
 * 联系方法测试用例
 * @author sunlingao@58.com
 * @date 2016年5月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class ContractTest {

      @Resource
      private IContractService contractService;
      
      @Test
      public void test(){
          CVContract t = new CVContract();
          t.setCoid("594a77e7-bcf9-44ec-911b-e310f0cc342b");
          Date date = new Date();
          t.setUpdateTime(date);
          contractService.update(t);
      }
}
