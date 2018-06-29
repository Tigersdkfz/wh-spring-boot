package com.sfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WhSpringBootApplication {

	public static void main(String[] args) {
		// SpringApplication.run(WhSpringBootApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(WhSpringBootApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		System.out.println("启动beanNames的个数：" + beanNames.length);
		/*
		 * for(String bn:beanNames){ System.out.println(bn); }
		 */
	}
}
