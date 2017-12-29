package com.deliverssmille.ahirsmile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deliverssmille.ahirsmile.config.PostgreSQLConfig;
import com.deliverssmille.ahirsmile.model.Quote;
import com.deliverssmille.ahirsmile.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@RestController
@RequestMapping(value = "/rest/student")
public class StudentService {

	@Autowired
	private MongoListServiceImp mongoListServiceImp;
	
	
	@Autowired
	PostgreSQLConfig postgreSQLConfig;
	
	

	/*
	 * this example to use restful web service to return all students in MongoDB
	 * http://localhost:8080/rest/student/
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HashMap<String, Student> getAllStudents() {

		HashMap<String, Student> hmStudent = mongoListServiceImp.getAllStudent();

		return hmStudent;
	}
	
	
	/*
	 * this example to use restful web service to return all students in MongoDB
	 * http://localhost:8080/rest/student/
	 */

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public HashMap<String, Student> createStudentsTab() {

		HashMap<String, Student> hmStudent = mongoListServiceImp.getAllStudent();
		
		int count=postgreSQLConfig.saveData();
		
		System.out.println("conut valuesss-----"+count);

		return hmStudent;
	}

	/*
	 * this example to use restful web service to add a student in mongodb using
	 * requestbody below url should be use to test in
	 * http://localhost:8080/rest/student/addNew
	 * "Advance Rest client tool with POST option" with sampleData
	 * 
	 * { "_id": 1513528991114, "name": "NewUser3", "subject": "Social3",
	 * "count": "1111" }
	 */
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public Student addNewStudent(@RequestBody Student student) throws Exception {

		DBObject dbObj = student.toDBObject();

		mongoListServiceImp.saveStdMongo(dbObj);

		return student;
	}

	// this example to use restful web service to update a student in MongoDB

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Student updateStudent(@RequestBody Student student) throws Exception {
		
		Student stdUpdated=null;
		Student std = mongoListServiceImp.getStd(student.get_id());

		WriteResult wrst = mongoListServiceImp.updateStd(std);
		
		if(wrst.wasAcknowledged()){
		 stdUpdated = mongoListServiceImp.getStd(student.get_id());
		}
		return stdUpdated;

		
	}

	/*
	 * this example to use restful web service to search a student in mongodb
	 * below url should be use to test in
	 * http://localhost:8080/rest/student/stdsearch/15135289911666
	 * "Advance Rest client tool with DELETE option"
	 */
	@RequestMapping(value = "stdsearch/{id}", method = RequestMethod.GET)
	public Student getStudentUsingId(@PathVariable String id) throws Exception {

		Student student = mongoListServiceImp.getStd(id);

		return student;
	}

	/*
	 * this example to use restful web service to delete a student from MongoDb
	 * below url should be use to test in
	 * "Advance Rest client tool with DELETE option"
	 * http://localhost:8080/rest/student/delete/1513528991114
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Student deleteStudent(@PathVariable String id) throws Exception {

		Student student = mongoListServiceImp.getStd(id);

		if (student != null) {

			WriteResult wrst = mongoListServiceImp.deleteStd(id);

		} else {
			throw new Exception("Student " + id + " does not exists");
		}
		return student;
	}

	// this example to use restful web service to consume webservice using
	// restTemplate
	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public Quote getAllQuote() {
		RestTemplate restTemplate = new RestTemplate();
		
		
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		// log.info(quote.toString());
		return quote;

	}

	// this example to consume restful web service using url api
	@RequestMapping(value = "/anyquotes", method = RequestMethod.GET)
	public Quote getAnyQuoteUsingHttp() {
		String quote = null;
		Quote quote1 = null;
		try {

			URL url = new URL("http://gturnquist-quoters.cfapps.io/api/random");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((quote = br.readLine()) != null) {
				System.out.println(quote);
				ObjectMapper mapper = new ObjectMapper();
				quote1 = mapper.readValue(quote, Quote.class);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return quote1;

	}

}
