package com.bj58.chr.data;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.utils.QDStringUtils;
import com.mongodb.DBObject;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月8日 下午5:54:06
 * @see
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class QueryTest {
    
    @Resource(name = "cvsMongoTemplate")
    private MongoTemplate ycMongoTemplate;
    
    /**
     * @param cvIdCoId
     * @return
     */
    @Test
    public void findOne() {
        String cvid = "57238c7be4b0a64f19fcc0c3";
        if (StringUtils.isNotEmpty(cvid)) {
            Query query = Query.query(Criteria.where("_id").is(cvid));
            query.fields().include("uid").include("lRefTime");
            CvInfoBo cvInfoBo = ycMongoTemplate.findOne(query, CvInfoBo.class, "cvInfoBo");
            if (cvInfoBo != null) {
                System.out.println(JSONUtils.writeValue(cvInfoBo));
            }
        }
    }
    
    @Test
    public void save(){
        CvInfoBo bo = new CvInfoBo();
        bo.setId(ObjectId.get().toString());
        System.out.println(bo.getId());
        bo.setMobile("1111112223");
        ycMongoTemplate.save(bo, "cvInfoBo");
    }
    
}
