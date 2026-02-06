package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.Data;

/**
 * 通用的客戶交易資料傳輸物件 (DTO)，用於封裝客戶的詳細資訊。
 * 這個類別被多個交易請求 (tranrq) 和回應 (tranrs) 重用。
 * 它還定義了用於不同操作（新增、修改）的驗證群組。
 */
@Data
public class TranData implements Serializable {
    /**
     * T001 (新增客戶) 交易的驗證群組介面。
     * 繼承 {@link Default} 以確保在驗證此群組時，
     * 也會驗證那些沒有指定群組的欄位。
     */
    public interface T001Interface extends Default {
    }

    /**
     * T002 (修改客戶) 交易的驗證群組介面。
     * 繼承 {@link Default} 以確保在驗證此群組時，
     * 也會驗證那些沒有指定群組的欄位。
     */
    public interface T002Interface extends Default {
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 訂單 ID。
     * 在修改情境 (T002Interface) 中為必填欄位。
     */
    @JsonProperty("orderId")
    @NotNull(groups = T002Interface.class) // 如果你有做分組驗證，可以把這行打開
    private Integer orderId;

    /**
     * 身分證字號。
     * 必填欄位，長度上限為 20。
     */
    @NotBlank(message = "身分證字號為必填")
    @Size(max = 20)
    @JsonProperty("idNum")
    private String idNum;

    /**
     * 中文姓名。
     * 必填欄位，長度上限為 20。
     */
    @NotBlank(message = "中文姓名為必填")
    @Size(max = 20)
    private String chineseName;

    /**
     * 性別。
     * 必填欄位，必須是 'm', 'M', 'f', 或 'F'。
     */
    @NotBlank(message = "性別為必填")
    @Pattern(regexp = "^[mMfF]$", message = "性別格式錯誤，僅限輸入 m 或 f")
    @Size(max = 20)
    private String gender;

    /**
     * 學歷。
     * 必填欄位，長度上限為 20。
     */
    @NotBlank(message = "學歷為必填")
    @Size(max = 20)
    private String education;

    /**
     * 戶籍地郵遞區號。
     * 必填欄位，必須是數字，長度上限為 20。
     */
    @NotBlank(message = "郵遞區號1為必填")
    @Pattern(regexp = "^\\d+$", message = "郵遞區號僅能輸入數字")
    @Size(max = 20)
    @JsonProperty("zipCode_1")
    // @JsonAlias("ZIP_CODE_1")
    private String zipCode1;

    /**
     * 戶籍地址。
     * 必填欄位，長度上限為 50。
     */
    @NotBlank(message = "地址1為必填")
    @Size(max = 50)
    private String address1;

    /**
     * 戶籍地電話。
     * 必填欄位，格式應為 "區碼-號碼" (例如: 02-12345678)。
     */
    @NotBlank(message = "戶籍電話為必填")
    @Pattern(regexp = "^\\d{2}\\d{7,8}$", message = "電話格式錯誤，應為區碼兩碼-號碼七或八碼 (例如: 02-12345678)")
    @Size(max = 20)
    @JsonProperty("telephone1")
    private String telephone1;

    /**
     * 現居地郵遞區號。
     * 必填欄位，必須是數字，長度上限為 20。
     */
    @NotBlank(message = "郵遞區號2為必填")
    @Pattern(regexp = "^\\d+$", message = "郵遞區號僅能輸入數字")
    @Size(max = 20)
    @JsonProperty("zipCode_2")
    // @JsonAlias("ZIP_CODE_2")

    private String zipCode2;

    /**
     * 現居地址。
     * 必填欄位，長度上限為 50。
     */
    @NotBlank(message = "地址2為必填")
    @Size(max = 50)
    private String address2;

    /**
     * 現居地電話。
     * 必填欄位，格式應為 "區碼-號碼"。
     */
    @NotBlank(message = "現居電話為必填")
    @Pattern(regexp = "^\\d{2}\\d{7,8}$", message = "電話格式錯誤，應為區碼兩碼-號碼七或八碼")
    @Size(max = 20)
    private String telephone2;

    /**
     * 行動電話。
     * 必填欄位，必須是數字。
     */
    @NotBlank(message = "行動電話為必填")
    @Pattern(regexp = "^\\d+$", message = "行動電話僅能輸入數字")
    @Size(max = 20)
    private String mobile;

    /**
     * 電子郵件。
     * 選填欄位，但如果填寫，必須是有效的 Email 格式。
     */
    @Pattern(regexp = "^$|^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email 格式不正確")
    @Size(max = 20)
    private String email;

    /**
     * 年資。
     */
    private Integer year;
}