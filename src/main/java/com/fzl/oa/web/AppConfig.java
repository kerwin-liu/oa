package com.fzl.oa.web;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.*;

@Configuration
@PropertySource("classpath:conf/webapp.properties")
@PropertySource(value = "classpath:conf/dev.properties", ignoreResourceNotFound = true)
@ComponentScan(basePackageClasses = { com.fzl.oa.web.Package.class })
@EnableTransactionManagement
public class AppConfig {
	public final static  Logger log = LoggerFactory.getLogger(AppConfig.class);

	public final static String SESSION_KEY = "user";

	@Autowired
	Environment env;
	@Bean
	public JsonParser jsonParser() {
		return new JsonParser();
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

}
