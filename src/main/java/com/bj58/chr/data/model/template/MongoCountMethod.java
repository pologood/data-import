package com.bj58.chr.data.model.template;

import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class MongoCountMethod extends CommonMethod {

	public MongoCountMethod(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@Override
	public Object doAction(BasicDBObject basicDBObject, BasicDBObject filedDBObject, String collectionName) {
		DBCollection dBCollection = this.getMongoTemplate().getCollection(collectionName);
		if (basicDBObject != null) {
			return dBCollection.count(basicDBObject);
		} else {
			return dBCollection.count();
		}
	}

}