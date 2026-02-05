package com.exam.exam;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 當將 Spring Boot 應用程式部署為傳統的 WAR 檔時，需要此類別來初始化 Servlet。
 * 它會將應用程式的配置指向主應用程式類別 ({@link ExamApplication})。
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * 配置應用程式以在 Servlet 容器中執行。
	 *
	 * @param application Spring 應用程式建構器。
	 * @return 配置好的應用程式建構器。
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExamApplication.class);
	}

}
