package com.fzl.oa.web.font;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { WebMVCConfig.class })
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setRequestContextAttribute("req");
		resolver.setExposeSessionAttributes(true);
		resolver.setExposeRequestAttributes(false);
		return resolver;
	}
	
	@Bean
    public FreeMarkerConfigurer freeMarkerConfig() throws FileNotFoundException, IOException{
        FreeMarkerConfigurer configurer =new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/view/");
        configurer.setDefaultEncoding("UTF-8");
        DefaultResourceLoader res = new DefaultResourceLoader();
        configurer.setConfigLocation(res.getResource("classpath:conf/freemarker.properties"));
        return configurer;
    }
}
