package com.exam.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 應用程式的進入點。
 */
@SpringBootApplication
public class ExamApplication {

	/**
	 * 主方法，用於啟動 Spring Boot 應用程式。
	 * @param args 命令列參數。
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

}
