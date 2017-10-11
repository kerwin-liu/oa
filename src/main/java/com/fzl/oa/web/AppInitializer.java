package com.fzl.oa.web;
import com.fzl.oa.web.*;
import com.fzl.oa.web.font.WebMVCConfig;
import com.fzl.oa.web.util.LoginFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(com.fzl.oa.web.AppConfig.class);
		rootContext.setServletContext(container);
		rootContext.refresh();
		
		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		container.addFilter("utf8Encoding", encodingFilter).addMappingForUrlPatterns(null, false, "/*");

		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setRedirectURL("/");
		loginFilter.setSessionKey(com.fzl.oa.web.AppConfig.SESSION_KEY);
		loginFilter.setIgnoreUrls("/", "/login", "/logout");
		container.addFilter("loginFilter", loginFilter).addMappingForUrlPatterns(null, false, "/*");
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.setParent(rootContext);
		webContext.register(WebMVCConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*", "/");

	}

}