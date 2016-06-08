package com.bj58.chr.data;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.data.model.BlackList;
import com.bj58.chr.data.service.IBlackListService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class BlackTest {
    
    @Resource
    private IBlackListService blackListService;
    
    @Test
    public void test(){
       BlackList black = new BlackList();
       black.setCvid("");
       black.setCoid("");
       black.setMobile("13269830963");
       blackListService.save(black);
    }

}
