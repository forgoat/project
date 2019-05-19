package com.hqei.data.process;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hqei.data.process.dao")
@ComponentScan("com.hqei")
public class HqeiDataProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(HqeiDataProcessApplication.class, args);
	}

}
