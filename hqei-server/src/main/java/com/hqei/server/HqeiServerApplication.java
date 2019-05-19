package com.hqei.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hqei.server.dao")
@ComponentScan("com.hqei")
public class HqeiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HqeiServerApplication.class, args);
	}

}
