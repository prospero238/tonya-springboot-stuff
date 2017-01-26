package com.throwawaycode;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class TonyasbannerApplication {

	private static final Logger LOG = LoggerFactory.getLogger(TonyasbannerApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(TonyasbannerApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner() {
		return new MyCommandLineRunner();
	}

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		return new WebMvcConfig();
	}

	private class MyCommandLineRunner implements CommandLineRunner {

		@Value("${what.to.say:IM_DEFINED_IN_THE_COMMANDLINERUNNER_ANNOTATION}")
		private String whatToSay;
		@Override
		public void run(String... strings) throws Exception {
			LOG.info(whatToSay);
		}
	}

	public class WebMvcConfig extends WebMvcConfigurerAdapter {
		@Resource
		MyInterceptor myInterceptor;


		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(myInterceptor).addPathPatterns("/saywhat/**");
		}


		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
	}
}
