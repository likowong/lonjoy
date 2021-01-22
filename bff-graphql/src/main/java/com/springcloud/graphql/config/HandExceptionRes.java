package com.springcloud.graphql.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author luke
 * @date 2021/1/22 23:21
 * @desc
 **/
@RestControllerAdvice
public class HandExceptionRes {
    /**
     * 自定义方法, 通过获取的Exception 判断Exception的异常信息来自定义返回值
     * 返回值可为json、ModelAndView、直接response 等等
     * @Author: fanyuke
     * @Date  : 2019年8月27日下午2:07:25
     * @param :  @param request
     * @param :  @param response
     * @param :  @param e
     * @param :  @return
     * @param :  @throws Exception
     * @return: Object
     */
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        // 得到请求URL地址时使用的方法
        String method = request.getMethod();
        // 定义一个返回的json对象(你们要返回什么自己来，把上面的返回值改一下就行了)
        JSONObject json = new JSONObject();

        // 捕捉请求方式
        if (e instanceof HttpRequestMethodNotSupportedException) {
            json.put("status", 0);
            json.put("msg", "不支持" + method + "请求.");
        }
        // 空指针异常
        if (e instanceof NullPointerException) {
            json.put("status", 0);
            json.put("msg", "系统错误");
        }
        // 抛出http异常
        if (e instanceof IllegalArgumentException) {
            throw new IllegalArgumentException();
        }

        /******************************Bean Validator*******************************/
        // 其实就是运行时异常
        if (e instanceof RuntimeException) {
            json.put("status", 0);
            json.put("msg", "系统异常");
        }
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            json.put("status", 0);
            json.put("msg", objectError.getDefaultMessage());
        }
        // Spring Validator参数校验异常处理
        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            json.put("status", 0);
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                String message = constraintViolation.getMessage();
                if (!StrUtil.isEmpty(message)) {
                    //直接返回第一个错误信息
                    json.put("msg", message);
                }
            }
            if (json.get("msg") == null || "".equals(json.get("msg"))) {
                json.put("msg", "参数错误");
            }
        }
        // Hibernate Validator参数校验异常处理
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            json.put("status", 0);
            json.put("msg", objectError.getDefaultMessage());
        }
        /******************************end Bean Validator*******************************/

        return json;
    }

}
