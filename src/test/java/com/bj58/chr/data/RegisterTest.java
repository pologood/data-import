package com.bj58.chr.data;

import java.io.Serializable;

import javax.annotation.Resource;




import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ISeekersCallBackService;

/**
 * 用户注册测试
 * @author sunlingao@58.com
 * @date 2016年4月9日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class RegisterTest {
    
    @Resource
    private ISeekersCallBackService seekersCallBackService;
    @Test
    public void registerTest(){
        Serializable sid = seekersCallBackService.createSeekers(new CvIdCoId("1111"));
        Assert.assertNotNull(sid);
        System.out.println("uid:"+String.valueOf(sid));
    }

}
