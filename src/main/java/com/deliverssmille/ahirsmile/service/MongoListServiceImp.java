package com.deliverssmille.ahirsmile.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.deliverssmille.ahirsmile.model.Student;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Component
public class MongoListServiceImp {

	private final MongoOperations mongoOperations;

	@Autowired
	public MongoListServiceImp(MongoTemplate mongoTemplate) {
		super();
		this.mongoOperations = (MongoOperations) mongoTemplate;
	}

	public Student getStd(String id) throws JsonParseException, JsonMappingException, IOException {

		DBObject dbObject = mongoOperations.getCollection("myCollection").findOne(new BasicDBObject("id", id));
		System.out.println("Object returned " + dbObject);

		Student std = mongoOperations.getConverter().read(Student.class, dbObject);

		return std;

	}

	public void saveStdMongo(DBObject dbObj) {

		DBCollection collection = mongoOperations.getCollection("myCollection");

		collection.insert(dbObj);

	}

	public HashMap<String, Student> getAllStudent() {

		HashMap<String, Student> hmstd = new HashMap<String, Student>();

		DBCursor dbCursor = mongoOperations.getCollection("myCollection").find();
		System.out.println("dbCursor from mongo is " + dbCursor.count());
		while (dbCursor.hasNext()) {

			DBObject dbObj = dbCursor.next();

			System.out.println("return Object from mongo is " + dbObj);
			if (dbObj != null) {

				Student stdNew = new Student();
				stdNew.set_id(dbObj.get("id").toString().trim());
				stdNew.setName(dbObj.get("name").toString().trim());
				stdNew.setSubject(dbObj.get("subject").toString().trim());

				hmstd.put(stdNew.get_id(), stdNew);

			}

		}

		return hmstd;
	}

	public WriteResult deleteStd(String id) {

		BasicDBObject document = new BasicDBObject();
		document.put("id", id);

		WriteResult dbObject = mongoOperations.getCollection("myCollection").remove(document);

		// TODO Auto-generated method stub
		return dbObject;
	}

	public WriteResult updateStd(Student std ) {

		BasicDBObject document = new BasicDBObject();
/*		document.append("$set", new BasicDBObject().append("id",std.get_id()));

		BasicDBObject searchQuery = new BasicDBObject().append("subject", std.getSubject());
*/
		document.append("$set", new BasicDBObject().append("subject", std.getSubject()));

		BasicDBObject searchQuery = new BasicDBObject().append("id",std.get_id());
		
		WriteResult dbObject = mongoOperations.getCollection("myCollection").update(searchQuery, document);

		return dbObject;
	}

}
