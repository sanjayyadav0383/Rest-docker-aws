package com.deliverssmille.ahirsmile.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class EncryptConfig {

	@Bean
	public static EnvironmentStringPBEConfig mongoDBEnvVariableConfiguration() {

		EnvironmentStringPBEConfig envVariableConfiguration = new EnvironmentStringPBEConfig();
		envVariableConfiguration.setAlgorithm("PBEWithMD5AndDES");
		envVariableConfiguration.setPasswordEnvName("MONGODB_PWD");
		envVariableConfiguration.setPassword("string");// need the string which
														// will be used to
														// generate
		return envVariableConfiguration;
	}

	@Bean
	public static StringEncryptor mongoDBConfigurationEncryptor() {

		StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
		configurationEncryptor.setConfig(mongoDBEnvVariableConfiguration());
		return configurationEncryptor;

	}
	
	@Bean
	@Profile("dev")
	public static EncryptablePropertyPlaceholderConfigurer propertyConfigurerDev(){
		EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(mongoDBConfigurationEncryptor());
		propertyConfigurer.setLocations(new ClassPathResource("application-dev.properties"));
		return propertyConfigurer;
		
		
	}
	
	@Bean
	@Profile("qa")
	public static EncryptablePropertyPlaceholderConfigurer propertyConfigurerQa(){
		EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(mongoDBConfigurationEncryptor());
		propertyConfigurer.setLocations(new ClassPathResource("application-qa.properties"));
		return propertyConfigurer;
		
		
	}
	
	@Bean
	@Profile("prod")
	public static EncryptablePropertyPlaceholderConfigurer propertyConfigurerProd(){
		EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(mongoDBConfigurationEncryptor());
		propertyConfigurer.setLocations(new ClassPathResource("application-prod.properties"));
		return propertyConfigurer;
		
		
	}

}
