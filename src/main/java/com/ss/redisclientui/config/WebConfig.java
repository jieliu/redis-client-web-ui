package com.ss.redisclientui.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ss.redisclientui")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setOrder(2);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public FreeMarkerViewResolver getFreeMarkerViewResolver() {
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
	    resolver.setSuffix(".ftl");
	    resolver.setOrder(1);
	    resolver.setContentType("text/html;charset=UTF-8");
	    return resolver;
	}
	
	@Bean
	public FreeMarkerConfigurer getFreeMarkerConfigurer() throws IOException, TemplateException {
	    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
	    
//	    freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
//	    cfg.setCacheStorage(new freemarker.cache.NullCacheStorage());
//	    configurer.setConfiguration(cfg);
	    configurer.setTemplateLoaderPath("/WEB-INF/views");
        configurer.setDefaultEncoding("UTF-8");
	    return configurer;
	}
	
	@Bean
	public JedisPool getJedisPool() {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1024);
		config.setMaxIdle(10);
		config.setMaxWaitMillis(1000);
		JedisPool pool = new JedisPool(config, "192.168.142.191", 6479, 2000);
		return pool;
	}
}
