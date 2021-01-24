package spring.cloud.provider.auth.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import spring.cloud.dubbo.api.HelloService;

/**
 * @author luke
 * @date 2021/1/24 16:41
 * @desc
 **/
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "dubbo-provider";
    }
}
