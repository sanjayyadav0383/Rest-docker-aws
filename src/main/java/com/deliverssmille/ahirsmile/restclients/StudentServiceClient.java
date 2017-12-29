package com.deliverssmille.ahirsmile.restclients;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.deliverssmille.ahirsmile.model.Student;

public class StudentServiceClient {
	
	private Client client;
	
	public StudentServiceClient(){
		
		init();
		
		
	}

	private void init() {
		
		ClientConfig clientConfig =new ClientConfig();
		
		HttpAuthenticationFeature httpAuthFeature=HttpAuthenticationFeature.basic("user", "");
		
		clientConfig.register(httpAuthFeature);
		clientConfig.register(JacksonFeature.class);
		
		client=ClientBuilder.newClient(clientConfig);
		
		
	}
	
	public Student getStudent(long id){
		
		WebTarget webTarget = client.target("http://localhost:8080/rest/student/");
		
		Student std =webTarget.path("stdsearch/"+id).request().get(Student.class);
		return null;
		
		
	}

}
