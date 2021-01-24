package spring.cloud.provider.auth.provider.service;



public interface IAuthenticationService {
    /**
     * 校验权限
     *
     * @return 是否有权限
     */
    boolean decide(String authentication, String url, String method);

}
