package spring.cloud.provider.auth.provider.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.cloud.common.Result;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    private final Result result;

    public CustomOauthException(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getSummary(), oAuth2Exception);
        this.result = Result.buildFailResBody();
    }
    public Result getResult(){
        return result;
    }
}