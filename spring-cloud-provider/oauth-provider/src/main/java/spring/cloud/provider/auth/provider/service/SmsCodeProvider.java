package spring.cloud.provider.auth.provider.service;


/**
 * todo 实现短信验证码的服务
 */
public interface SmsCodeProvider {

    String getSmsCode(String mobile, String businessType);
}
