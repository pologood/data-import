package com.bj58.chr.data.service.impl;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.ISeekerUserDao;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.service.ISeekerUserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service
public class SeekerUserService extends SuperService<SeekerUser> implements ISeekerUserService {
    
    Logger logger = Logger.getLogger(SeekerUserService.class);
    private final String TABLE = "jobseekersBo";

    @Resource
    private ISeekerUserDao seekUserDao;

    @Resource(name = "userMongoTemplate")
    private MongoTemplate userTemplate;

    @Override
    public ISuperDao<SeekerUser> getSuperDao() {
        return seekUserDao;
    }

    @Override
    public SeekerUser findByCoId(Serializable coId) {
        return seekUserDao.findByCoId(coId);
    }

    public void updateSeekersLink(String uid, String email, String mobile) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("accountStatus", 0);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(uid));
        Update update = new Update();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            update.set(key, params.get(key));
        }
        userTemplate.updateFirst(query, update, TABLE);
    }

    public Map getUserIsExistForMobile(String mobile, String coid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mobile").is(mobile));
        Map params = userTemplate.findOne(query, Map.class, TABLE);
        if(params == null){
            return null;
        }else{
            String uid = params.get("_id").toString();
            if(logger.isDebugEnabled())
            logger.debug(String.format("=========getUserForMobile mobile is %s coid is %s find uid is %s=====", mobile,coid,uid));
            return params;
        }
    }

    @Override
    public Map<String,String> getUserInfo(String uid) {
        Map<String,String> map = null;
        Query query = Query.query(Criteria.where("_id").is(uid));
        
        BasicDBObject bdb = new BasicDBObject();
        bdb.append("_id", 1);
        bdb.append("email", 1);
        bdb.append("mobile", 1);

        DBCursor dBCursor = userTemplate.getCollection(TABLE).find(query.getQueryObject(), bdb);
        if (dBCursor != null) {
            while (dBCursor.hasNext()) {
                map = new HashMap<String,String>();
                DBObject dbo = dBCursor.next();
                map.put("email", String.valueOf(dbo.get("email")));
                map.put("mobile", String.valueOf(dbo.get("mobile")));
            }
        }
        return map;
    }

}
