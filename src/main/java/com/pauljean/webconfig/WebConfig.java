package com.pauljean.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.pauljean" })
public class WebConfig extends WebMvcConfigurerAdapter {
	
	   @Override
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      registry.jsp().prefix("/WEB-INF/view/").suffix(".jsp");
	   }
	   
	   
		@Bean
		public RequestContextListener requestContextListener() {
			return new RequestContextListener();
		}
		
		  @Override
		    public void addResourceHandlers(ResourceHandlerRegistry registry) {
			  
		        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		    }

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}

	   @Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	      registry.addViewController("/login").setViewName("login");
	      
	   }

}
