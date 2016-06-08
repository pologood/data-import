package com.bj58.chr.data;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.handler.ICVContentHandler;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICvInfoBoService;

/**
 * qd简历转换yc简历
 * @author sunlingao@58.com
 * @date 2016年4月6日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class QdCvConvertTest{
    
    @Resource(name="qdCvContentHandler")
    private ICVContentHandler cvContentHandler;
    
    @Resource
    private ICvInfoBoService cvInfoService;
    
    @Test
    public void testConvert() throws Exception{
        CVModel model = new ConvertTest().Test();
        CvInfoBo bo = cvContentHandler.build(model);
        bo.setMobile("18581628788");
        System.out.println(JSONUtils.writeValue(bo));
    }
    
    @Test
    public void testSave() throws Exception{
        CVModel model = new ConvertTest().Test();
        CvInfoBo bo = cvContentHandler.build(model);
        bo.setMobile("18581628788");
        cvInfoService.save(bo,new CvIdCoId("qd-111-222"));
    }
    
    @Test
    public void testUpdate() throws Exception{
        CVModel model = new ConvertTest().Test();
        CvInfoBo bo = cvContentHandler.build(model);
        bo.setMobile("13333333333");
        bo.setLRefTime(new Date().getTime());
        cvInfoService.saveOrUpdate(bo,"qd-111-222");
    }
    
}
