package spring.cloud.bff.restfulapi.web;

import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.dubbo.api.HelloService;

/**
 * @author luke
 * @date 2021/1/24 17:03
 * @desc
 **/
@Api(tags = "dubbo相关")
@RestController
public class DubboController {
    @DubboReference
    private HelloService helloService;

    @RequestMapping(value = "/syaHello",method = RequestMethod.GET)
    public String sayHello(){
        return helloService.sayHello();
    }
}
