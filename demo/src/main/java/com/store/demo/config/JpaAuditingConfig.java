package com.store.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

/**
 * @Configuration：告訴 Spring 框架，這是一個設定檔類別。
 * @EnableJpaAuditing(auditorAwareRef = "auditorProvider"):
 *   啟用 JPA Auditing 功能。這個功能可以自動記錄實體 (Entity) 的建立者、
 *   最後修改者、建立時間、最後修改時間。
 *   auditorAwareRef = "auditorProvider" 指定了用哪一個 Bean 來提供當前的操作者資訊。
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    /**
     * @Value("${user.empID:DEFAULT_USER}"):
     *   從 application.properties 檔案中讀取 `user.empID` 的值，並注入到 empID 屬性中。
     *   如果找不到 `user.empID`，則使用預設值 "DEFAULT_USER"。
     */
    @Value("${user.empID:DEFAULT_USER}")
    private String empID;

    /**
     * @PostConstruct:
     *   這個註解標記的方法，會在 JpaAuditingConfig 這個 Bean 被建立並完成依賴注入後自動執行。
     *   這裡用來在啟動時印出 empID 的值，方便除錯。
     */
    @PostConstruct
    public void printCheck() {
        System.err.println(empID);
    }

    /**
     * @Bean: 告訴 Spring 容器，這個方法會回傳一個物件，請將這個物件納入 Spring 管理。
     *        這個 Bean 的名稱就是方法名稱 "auditorProvider"。
     * @return AuditorAware<String>
     *   這是一個介面，我們需要實作它來告訴 JPA Auditing 當前的操作者是誰。
     *   這裡使用 Lambda 表示式，回傳一個包含 empID 的 Optional 物件。
     *   這樣，當我們使用 @CreatedBy 或 @LastModifiedBy 時，
     *   JPA 就會自動將這裡提供的 empID 填入對應的欄位。
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(empID);
    }
}