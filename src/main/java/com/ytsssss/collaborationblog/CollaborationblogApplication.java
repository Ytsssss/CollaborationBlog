package com.ytsssss.collaborationblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ytsssss.collaborationblog.mapper")
public class CollaborationblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollaborationblogApplication.class, args);
	}
}
