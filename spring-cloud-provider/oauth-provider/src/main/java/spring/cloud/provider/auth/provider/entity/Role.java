package spring.cloud.provider.auth.provider.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role{
    private String code;
    private String name;
    private String description;
}
