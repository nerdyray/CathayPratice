package com.store.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication: 這是 Spring Boot 的核心註解。
 *                         它是一個複合註解，包含了以下三個主要功能：
 *                         1. @EnableAutoConfiguration: 啟用 Spring Boot 的自動設定功能，
 *                         Spring 會根據專案的依賴 (例如 spring-boot-starter-web)
 *                         自動設定相關的元件。
 *                         2. @ComponentScan: 自動掃描這個類別所在的套件及其子套件中所有的 Spring 元件
 *                         (例如 @Component, @Service, @Repository, @Controller)。
 *                         3. @Configuration: 允許在這個類別中使用 @Bean 來定義 Bean。
 */
@SpringBootApplication
public class StoreApplication {

	/**
	 * 這是 Java 應用程式的進入點 (Entry Point)。
	 * `SpringApplication.run(StoreApplication.class, args);`
	 * 這行程式碼會啟動整個 Spring Boot 應用程式。
	 * 
	 * @param args 命令列參數
	 */
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
