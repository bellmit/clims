package com.bazl.clims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;



@SpringBootApplication
@MapperScan("com.bazl.clims.dao")//不加会报NoSuchBeanDefinitionException
@ImportResource("classpath:applicationContext-cxf-servlet.xml")
@EnableScheduling
@EnableTransactionManagement
//@EnableRedisHttpSession
public class ClimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimsApplication.class, args);
	}

}

