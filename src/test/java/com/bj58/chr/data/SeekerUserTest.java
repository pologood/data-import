package com.bj58.chr.data;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.ISeekersCallBackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class SeekerUserTest {
    
    @Resource
    private ISeekerUserService seekerUserService;
    
    @Resource
    private ISeekersCallBackService seekerCallBackService;
    
    @Test
    public void save(){
        SeekerUser user = new SeekerUser("11111111","5555555","12345");
        seekerUserService.save(user);
    }
    
    @Test
    public void update(){
        seekerCallBackService.updateSeekersLink("9334b0e45ad10d570d72b06ci","651669789@qq.com", "112345678901");
    }
    
}
