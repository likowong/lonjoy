package com.lonjoy.framework.web;


import com.lonjoy.common.ApplicationException;
import com.lonjoy.common.ApplicationResponseCode;
import com.lonjoy.common.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理，由spring mvc拦截异常，做统一的处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResResult processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        log.error("mvc拦截异常，{}，{}", e.getMessage(), e);
        // 业务异常处理
        if (e instanceof ApplicationException) {
            return ResResult.fail(new ApplicationResponseCode(((ApplicationException) e).getErrorCode(), ((ApplicationException) e).getErrorStatus(), ((ApplicationException) e).getContent()));
        }
        return ResResult.fail();
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResResult handleBindException(BindException ex) {
        log.error("校验异常处理器handleBindException:{}", ex);
        StringBuilder errorMsg = new StringBuilder();
        // 获取所有字段验证出错的信息
        List<FieldError> allErrors = ex.getFieldErrors();
        allErrors.forEach(fieldError -> errorMsg.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("; "));
        return ResResult.buildFailTextResBody(ApplicationResponseCode.BIZ_ERROR_STATUS, errorMsg.toString());

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("校验异常处理器handleMethodArgumentNotValidException:{}", ex);
        BindingResult bindingResult = ex.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().get(0);
        return ResResult.buildFailTextResBody(ApplicationResponseCode.BIZ_ERROR_STATUS, objectError.getDefaultMessage());
    }

    /**
     * Spring Validator参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResResult handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            if (!StringUtils.isEmpty(message)) {
                //直接返回第一个错误信息
                return ResResult.buildFailTextResBody(ApplicationResponseCode.BIZ_ERROR_STATUS, message);
            }
        }
        return ResResult.fail();
    }
}
