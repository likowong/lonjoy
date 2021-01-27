package spring.cloud.provider.auth.provider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luke
 * @date 2021/1/27 22:34
 * @desc
 **/
@Api(tags = "测试相关")
@RestController
public class DemoController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "测试方法")
    public String test(){
        return "123";
    }
}
