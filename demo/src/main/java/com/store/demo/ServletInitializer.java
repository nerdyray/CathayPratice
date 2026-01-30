package com.store.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 這是一個 Servlet 初始化器。
 * 當你需要將 Spring Boot 應用程式打包成一個傳統的 WAR 檔案，
 * 並部署到外部的 Servlet 容器 (例如 Tomcat, Jetty) 中時，就需要這個類別。
 * 它會告訴 Servlet 容器如何啟動你的 Spring Boot 應用程式。
 * 如果你是直接執行 JAR 檔案，那麼這個類別不會被用到。
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * 這個方法會設定應用程式的主要來源 (也就是 @SpringBootApplication 所在的類別)，
	 * 讓 Servlet 容器知道要從哪裡開始設定 Spring 應用程式。
	 * @param application Spring 應用程式建構器
	 * @return 設定好的建構器
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StoreApplication.class);
	}

}
