package com.bj58.chr.data.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.dao.ICvInfoBoDao;
import com.bj58.chr.data.model.yccv.CvInfoBo;

@Service
public class CvInfoDao implements ICvInfoBoDao {

	private Logger logger = Logger.getLogger(CvInfoBo.class);

	@Resource
	private MongoTemplate mongoTemplate;
	
    @Resource(name = "cvsMongoTemplate")
    private MongoTemplate ycMongoTemplate;

	@Override
	public long count(Map<String, Object> params) {
		return 0L;
	}

	@Override
	public List<CvInfoBo> list(Map<String, Object> params) {
		return null;
	}

	@Override
	public CvInfoBo findById(Serializable id) {
	    Query query = Query.query(Criteria.where("_id").is(id));
	    return mongoTemplate.findOne(query, CvInfoBo.class, getTableName());
	}

	private final String getTableName() {
		return TABLE;
	}

	@Override
	public Serializable save(CvInfoBo t) {
		mongoTemplate.save(t, getTableName());
		return t.getId();
	}

	@Override
	public void update(CvInfoBo t) {
	}

	@Override
	public void remove(Serializable id) {
	}

	@Override
	public void updateByField(String id, Map<String, Object> params) {
		if (params == null) {
			throw new NullPointerException("params is null");
		}
		Query query = Query.query(Criteria.where("_id").is(id));
		Update update = new Update();
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value != null)
				update.set(key, value);
		}
		logger.info(String.format("CvInfoDao.update query is %s,update is %s", query.toString(), update.toString()));
		mongoTemplate.updateFirst(query, update, TABLE);
		ycMongoTemplate.updateFirst(query, update, TABLE);
	}

	@Override
	public Serializable saveOrUpdate(CvInfoBo t) {
		return save(t);
	}

	@Override
	public int getCvCountForUid(String uid) {
		Query query = Query.query(Criteria.where("uid").is(uid).and("isDel").is("0"));
		return (int) mongoTemplate.count(query, TABLE);
	}

	@Override
	public List<String> getCvidsForUid(String uid) {
		Query query = Query.query(Criteria.where("uid").is(uid).and("isDel").is("0"));
		query.fields().include("_id");
		return mongoTemplate.find(query, String.class, TABLE);
	}
	
    @Override
    public int getCvCountByTime(String key, long date) {
        Query query = Query.query(Criteria.where(key).gte(date));
        if(logger.isDebugEnabled()){
            logger.debug(query.toString());
        }
        return (int) mongoTemplate.count(query, TABLE);
    }

    @Override
    public List<CvInfoBo> getCvCountByTimePageList(String key, long date, Direction sort, int skip,
            int limit) {
        Query query = Query.query(Criteria.where(key).gte(date));
        query.with(new Sort(sort, key));
        query.skip(skip).limit(limit);
        logger.info(String.format("%s and skip is %s and  limit is %s",query.toString(), skip,limit));
        return mongoTemplate.find(query, CvInfoBo.class);
    }

}
