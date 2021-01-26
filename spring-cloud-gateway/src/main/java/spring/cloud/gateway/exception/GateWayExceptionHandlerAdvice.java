package spring.cloud.gateway.exception;

import com.spring.cloud.common.Result;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.spring.cloud.common.GlobalResponseCode.SYS_FAILED;

@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public Result handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return Result.buildFailResBody(SYS_FAILED);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Result handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Result.buildFailResBody(SYS_FAILED);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Result.buildFailResBody(SYS_FAILED);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.buildFailResBody();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Result.buildFailResBody();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handle(Throwable throwable) {
        Result result = Result.buildFailResBody();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}