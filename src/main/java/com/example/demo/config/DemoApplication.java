package com.example.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
@EntityScan(basePackages = {"com.example.demo"})
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@Bean
	ServletRegistrationBean<Servlet> jsfServletRegistration (ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		ServletRegistrationBean<Servlet> srb = new ServletRegistrationBean<>();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(List.of("*.xhtml"));
		srb.setLoadOnStartup(1);
		return srb;
	}
}
