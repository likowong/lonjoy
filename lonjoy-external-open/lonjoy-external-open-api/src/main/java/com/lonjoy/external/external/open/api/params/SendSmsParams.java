package com.lonjoy.external.external.open.api.params;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @TableName account
 */
@Data
public class SendSmsParams implements Serializable {
    /**
     * 手机号
     */
    @NotNull(message = "姓名不能为空")
    private String phone;

    /**
     * 短信内容
     */
    @NotNull(message = "短信内容为空")
    private String message;
}
