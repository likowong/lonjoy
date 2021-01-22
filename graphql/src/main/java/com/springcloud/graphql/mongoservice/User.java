package com.springcloud.graphql.mongoservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author luke
 * @date 2021/1/15 0015 11:33
 * @desc
 **/
@Data
@ToString
@Accessors(chain = true)
public class User {
    @MongoId
    private String id;
    private String name;
    private String sex;
    private Integer salary;
    @NotNull(message = "age为空")
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    @NotNull(message = "remake为空")
    private String remake;

}
