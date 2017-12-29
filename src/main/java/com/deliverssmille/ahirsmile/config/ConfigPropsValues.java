package com.deliverssmille.ahirsmile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableAutoConfiguration
public class ConfigPropsValues {

	@Value("${mongodburl}")
	private String mongoDbUrl;

	@Value("${mongodbname}")
	private String mongoDbName;

	@Value("${postgrsdbuser}")
	private String postgrsDbuser;

	@Value("${postgrsdburl}")
	private String postgrsDburl;

	@Value("${postgrsdbpassword}")
	private String postgrsDbpassword;

	@Value("${postgrsdbdriver}")
	private String postgrsDbdriver;

	public String getMongoDbName() {
		return mongoDbName;
	}

	public String getMongoDbUrl() {
		return mongoDbUrl;
	}

	public String getPostgrsDbdriver() {
		return postgrsDbdriver;
	}

	public String getPostgrsDbpassword() {
		return postgrsDbpassword;
	}

	public String getPostgrsDburl() {
		return postgrsDburl;
	}

	public String getPostgrsDbuser() {
		return postgrsDbuser;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public void setMongoDbUrl(String mongoDbUrl) {
		this.mongoDbUrl = mongoDbUrl;
	}

	public void setPostgrsDbdriver(String postgrsDbdriver) {
		this.postgrsDbdriver = postgrsDbdriver;
	}

	public void setPostgrsDbpassword(String postgrsDbpassword) {
		this.postgrsDbpassword = postgrsDbpassword;
	}

	public void setPostgrsDburl(String postgrsDburl) {
		this.postgrsDburl = postgrsDburl;
	}

	public void setPostgrsDbuser(String postgrsDbuser) {
		this.postgrsDbuser = postgrsDbuser;
	}

}
