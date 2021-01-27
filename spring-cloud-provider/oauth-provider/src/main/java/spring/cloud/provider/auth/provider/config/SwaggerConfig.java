package spring.cloud.provider.auth.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luke
 * @date 2021/1/27 21:11
 * @desc
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //加载配置信息
                .apiInfo(apiInfo())
                .select()
                //加载swagger 扫描包
                .apis(RequestHandlerSelectors.basePackage("spring.cloud.provider.auth.provider"))
                .paths(PathSelectors.any()).build()
                //设置安全认证
                .securitySchemes(security());

    }

    /**
     * 获取swagger创建初始化信息
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("luke", "", "15010343670@163.com");
        return new ApiInfoBuilder().title("swagger api文档").description("dev by luke").contact(contact)
                .version("1.0.0").build();
    }
    /**
     * 安全认证参数
     * @return
     */
    private List<ApiKey> security() {
        List<ApiKey> list=new ArrayList<>();
        list.add(new ApiKey("jwt", "Authorization", "header"));
        return list;
    }
}
