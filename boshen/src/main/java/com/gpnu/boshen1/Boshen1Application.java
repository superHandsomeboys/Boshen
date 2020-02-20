package com.gpnu.boshen1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.gpnu.boshen1.Mapper")
@SpringBootApplication
public class Boshen1Application {

	public static void main(String[] args) {
		SpringApplication.run(Boshen1Application.class, args);
	}

}
