package spring.cloud.provider.auth.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author luke
 */
@MapperScan(basePackages = "spring.cloud.provider.auth.provider.dao")
@DubboComponentScan
@SpringBootApplication
@EnableDiscoveryClient
public class AuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthProviderApplication.class, args);
    }

}
