package com.store.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component: 告訴 Spring 框架，這個類別是一個通用的元件，應該被 Spring 容器管理。
 * @ConfigurationProperties(prefix = "user"):
 *   這個註解會將 application.properties (或 application.yml) 檔案中，
 *   前綴為 "user" 的設定值，自動綁定到這個類別的屬性上。
 *   例如，如果 application.properties 中有 `user.empID=E12345`，
 *   那麼這個類別的 empID 屬性就會被設定為 "E12345"。
 */
@Component
@ConfigurationProperties(prefix = "user")
public class UserObject {
    // 員工 ID
    private String empID;

    // empID 的 getter 方法
    public String getEmpID() {
        return empID;
    }

    // empID 的 setter 方法
    public void setEmpID(String empID) {
        this.empID = empID;
    }
}
