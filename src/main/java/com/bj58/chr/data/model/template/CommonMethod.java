package com.bj58.chr.data.model.template;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月8日 下午8:03:30
 * @see
 * @since
 */
public abstract class CommonMethod implements TemplateMethodModelEx {

	private MongoTemplate mongoTemplate;

	public CommonMethod(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments.size() == 3) {
			String query = ((SimpleScalar) arguments.get(0)).getAsString();
			String fileds = ((SimpleScalar) arguments.get(2)).getAsString();
			String collectionName = ((SimpleScalar) arguments.get(3)).getAsString();
			BasicDBObject queryDBObject = null;
			if (!StringUtils.isEmpty(query)) {
				queryDBObject = new BasicDBObject();
				DBObject dbObject = (DBObject) JSON.parse(query);
				queryDBObject.putAll(dbObject);
			}
			BasicDBObject filedDBObject = null;
			if (!StringUtils.isEmpty(fileds)) {
				filedDBObject = new BasicDBObject();
				DBObject dbObject = (DBObject) JSON.parse(fileds);
				filedDBObject.putAll(dbObject);
			}
			return doAction(queryDBObject, filedDBObject, collectionName);
		}
		return new Object();
	}

	public abstract Object doAction(BasicDBObject basicDBObject, BasicDBObject filedDBObject, String collectionName);

}
