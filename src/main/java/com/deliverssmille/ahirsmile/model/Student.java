package com.deliverssmille.ahirsmile.model;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class Student {

	private String _id;
	private String name;
	private String subject;
	// private long count;
	int lLimit = 1;

	long rLimit = 100L;

	public Student() {
	}

	public Student(String id, String name, String subject) {
		super();
		this._id = id;
		this.name = name;
		this.subject = subject;
		// this.count=count;
	}

	public String get_id() {
		return _id;
	}

	/*
	 * public long getId() { return id; }
	 */
	public String getName() {
		return name;
	}

	public String getSubject() {
		return subject;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	/*
	 * public void setId(long id) { this.id = id; }
	 */

	public void setName(String name) {
		this.name = name;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [_id=" + _id + ", name=" + name + ", subject=" + subject + "]";
	}

	// add for converting java object to mongo object
	public DBObject toDBObject() {

		BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start("id", _id).append("name", name)
				.append("subject", subject);

		return documentBuilder.get();

	}

}
