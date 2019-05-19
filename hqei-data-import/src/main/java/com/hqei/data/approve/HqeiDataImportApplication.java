package com.hqei.data.approve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hqei.data.approve.dao")
@ComponentScan("com.hqei")
public class HqeiDataImportApplication {

	public static void main(String[] args) {
		SpringApplication.run(HqeiDataImportApplication.class, args);
	}

}
