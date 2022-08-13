package com.lonjoy.gateway.exception;

import com.lonjoy.common.ApplicationException;
import com.lonjoy.common.ApplicationResponseCode;
import com.lonjoy.common.ResResult;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.lonjoy.common.ApplicationResponseCode.SYS_FAILED;

@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResResult handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return ResResult.fail(SYS_FAILED);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResResult handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return ResResult.fail(SYS_FAILED);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResResult handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return ResResult.fail(SYS_FAILED);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResResult handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return ResResult.fail();
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResResult handle(ApplicationException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return ResResult.fail(new ApplicationResponseCode(((ApplicationException) ex).getErrorCode(), ((ApplicationException) ex).getErrorStatus(), ((ApplicationException) ex).getContent()));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResResult handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return ResResult.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResResult handle(Throwable throwable) {
        ResResult resResult = ResResult.fail();
        if (throwable instanceof ResponseStatusException) {
            resResult = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            resResult = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            resResult = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            resResult = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            resResult = handle((Exception) throwable);
        }
        if (throwable instanceof ApplicationException) {
            resResult = handle((ApplicationException) throwable);
        }
        return resResult;
    }
}
