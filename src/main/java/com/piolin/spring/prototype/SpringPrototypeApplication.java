package com.piolin.spring.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Arrays;

/*
SpringBootApplication is a convenience annotation that adds / executes the following three annotations:

@Configuration: Tags the class as a source of bean definitions for the application context.
@EnableAutoConfiguration: Tells SpringBoot to start adding beans based on classpath settings, other beans, and various property settings.
For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors,
such as setting up a DispatcherServlet.
@ComponentScan: Tells Spring to look for other components, configurations, and services in the package, letting it find the
controllers.

Excluded when using SpringSecurity AuthE with API Keys
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

 */
@SpringBootApplication
@EntityScan("com.piolin.spring.prototype.database.entity")
@EnableJpaRepositories("com.piolin.spring.prototype.database.repo")
@ComponentScan(basePackages = {"com.piolin.spring.prototype"})
public class SpringPrototypeApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringPrototypeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringPrototypeApplication.class, args);
		LOG.info("Maven-Java SpringBoot Application (spring-prototype) context initialized ...");
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) { // ClientRepository could be injected here to test
		return args -> {

			LOG.info("Let's inspect the Beans provided by SpringBoot Framework:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				LOG.info("Bean name: {}", beanName);
			}

			LOG.info("Arguments Array: {} ", Arrays.stream(args).sorted().toList());
		};
	}

}