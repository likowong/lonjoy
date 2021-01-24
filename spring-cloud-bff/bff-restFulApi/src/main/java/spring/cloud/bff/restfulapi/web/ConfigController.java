package spring.cloud.bff.restfulapi.web;

import spring.cloud.bff.restfulapi.service.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author luke
 * @date 2020/12/24 0024 11:34
 * @desc
 **/
@Controller
@RefreshScope
public class ConfigController {
    @Autowired
    private NacosService nacosService;

    @Value(value = "${useLocalCache:1234}")
    private String useLocalCache;

    @Value(value = "${apolloText:1234}")
    private String apolloText;

    public void setApolloText(String apolloText) {
        this.apolloText = apolloText;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return useLocalCache;
    }

    @RequestMapping(value = "/getApollo", method = GET)
    @ResponseBody
    public String getApollo() {
        return apolloText;
    }

    @RequestMapping(value = "/hello", method = GET)
    @ResponseBody
    public String hello(String serviceName) throws InterruptedException {
        return nacosService.hello(serviceName);
    }
}
