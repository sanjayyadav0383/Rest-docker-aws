package com.deliverssmille.ahirsmile.config;


import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.deliverssmille.ahirsmile.AhirsmileApplication;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@Configuration
@Profile({"!test"})
public class MongoConfig extends AbstractMongoConfiguration {
	
	
	@Autowired
	ConfigPropsValues configPropsValues;
	
	private static final Logger log = LoggerFactory.getLogger(AhirsmileApplication.class);
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	ConfigurableEnvironment environment;
	
	
	@Bean
	public MongoOperations mongoOperations() throws Exception{
		
		environment=(ConfigurableEnvironment) applicationContext.getEnvironment();
		
		//logger.debug(""+)
		
		log.info("come in mongo operations");
		System.out.println("Active Profile is "+Arrays.deepToString(environment.getActiveProfiles()));
		
		return new MongoTemplate(mongo(),getDatabaseName());
		
	}
	
	@Bean
	public MongoMappingContext springDataMongoMappingContext(){
		return new MongoMappingContext();
		
		
	}



	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return configPropsValues.getMongoDbName();//"myNewDatabase";
	}

	@Override
	public Mongo mongo() throws Exception {
		
		MongoClientURI uri = new MongoClientURI(configPropsValues.getMongoDbUrl(),MongoClientOptions.builder().cursorFinalizerEnabled(false));
		return new MongoClient(uri);
		
		//return new MongoClient(configPropsValues.getMongoDbUrl(), 27017);
	}



	

}
