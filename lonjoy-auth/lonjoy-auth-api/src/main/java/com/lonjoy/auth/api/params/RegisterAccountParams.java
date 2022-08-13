package com.lonjoy.auth.api.params;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @TableName account
 */
@Data
public class RegisterAccountParams implements Serializable {
    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;
}
