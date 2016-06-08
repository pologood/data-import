package com.bj58.chr.data.model.template;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class MongoQueryMethod extends CommonMethod {

	public MongoQueryMethod(MongoTemplate mongoTemplate) {
		super(mongoTemplate);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object doAction(BasicDBObject basicDBObject, BasicDBObject filedDBObject, String collectionName) {
		DBCollection dBCollection = this.getMongoTemplate().getCollection(collectionName);
		DBCursor dBCursor = null;
		if (basicDBObject == null) {
			dBCursor = dBCollection.find(basicDBObject, filedDBObject);
		} else {
			dBCursor = dBCollection.find();
		}
		List<Map> dbObjects = Lists.newArrayList();
		while (dBCursor.hasNext()) {
			dbObjects.add(dBCursor.next().toMap());
		}
		return dbObjects;
	}

}