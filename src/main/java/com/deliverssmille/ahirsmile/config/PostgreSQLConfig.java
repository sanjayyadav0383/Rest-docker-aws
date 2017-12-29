package com.deliverssmille.ahirsmile.config;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration
public class PostgreSQLConfig {

	@Autowired
	ConfigPropsValues configPropsValues;
	
	DataSource dataSource=null;

	Connection conn = null;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName(configPropsValues.getPostgrsDbdriver());
		driver.setUrl(configPropsValues.getPostgrsDburl());
		driver.setUsername(configPropsValues.getPostgrsDbuser());
		driver.setPassword(configPropsValues.getPostgrsDbpassword());

		return driver;

	}
	
	   public int saveData() {
		      Connection c = null;
		      Statement stmt = null;
		      int count=0;
		      try {
		        // Class.forName("org.postgresql.Driver");
		         c = dataSource().getConnection();
		         System.out.println("Opened database successfully");

		         stmt = c.createStatement();
		         
		         String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
		              stmt.executeUpdate(sql);

		              sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
		                 + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
		         
/*		         String sql = "CREATE TABLE COMPANY " +
		            "(ID INT PRIMARY KEY     NOT NULL," +
		            " NAME           TEXT    NOT NULL, " +
		            " AGE            INT     NOT NULL, " +
		            " ADDRESS        CHAR(50), " +
		            " SALARY         REAL)";*/
		        count= stmt.executeUpdate(sql);
		         stmt.close();
		         c.close();
		      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Table created successfully");
			return count;
		   }

}
