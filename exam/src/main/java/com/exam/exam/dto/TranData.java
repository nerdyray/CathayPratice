package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TranData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "身分證字號為必填")
    @Size(max = 20)
    @JsonProperty("idNum")
    private String idNum;

    @NotBlank(message = "中文姓名為必填")
    @Size(max = 20)
    private String chineseName;

    @NotBlank(message = "性別為必填")
    @Pattern(regexp = "^[mMfF]$", message = "性別格式錯誤，僅限輸入 m 或 f")
    @Size(max = 20)
    private String gender;

    @NotBlank(message = "學歷為必填")
    @Size(max = 20)
    private String education;

    @NotBlank(message = "郵遞區號1為必填")
    @Pattern(regexp = "^\\d+$", message = "郵遞區號僅能輸入數字")
    @Size(max = 20)
    @JsonProperty("zipCode_1")
    private String zipCode1;

    @NotBlank(message = "地址1為必填")
    @Size(max = 50)
    private String address1;

    @NotBlank(message = "戶籍電話為必填")
    @Pattern(regexp = "^\\d{2}-\\d{7,8}$", message = "電話格式錯誤，應為區碼兩碼-號碼七或八碼 (例如: 02-12345678)")
    @Size(max = 20)
    @JsonProperty("telephone1")
    private String telephone1;

    @NotBlank(message = "郵遞區號2為必填")
    @Pattern(regexp = "^\\d+$", message = "郵遞區號僅能輸入數字")
    @Size(max = 20)
    @JsonProperty("zipCode_2")
    private String zipCode2;

    @NotBlank(message = "地址2為必填")
    @Size(max = 50)
    private String address2;

    @NotBlank(message = "現居電話為必填")
    @Pattern(regexp = "^\\d{2}-\\d{7,8}$", message = "電話格式錯誤，應為區碼兩碼-號碼七或八碼")
    @Size(max = 20)
    private String telephone2;

    @NotBlank(message = "行動電話為必填")
    @Pattern(regexp = "^\\d+$", message = "行動電話僅能輸入數字")
    @Size(max = 20)
    private String mobile;

    @Pattern(regexp = "^$|^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email 格式不正確")
    @Size(max = 20)
    private String email;

    private Integer year;
}