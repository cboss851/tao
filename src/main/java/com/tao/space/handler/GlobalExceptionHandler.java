package com.tao.space.handler;


import com.tao.commons.result.ResponseCodeEnum;
import com.tao.commons.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult exception(RuntimeException ex, HttpServletRequest request) {
        log.error("来源URL：{},异常：{}", request.getRequestURL(), ex.toString());
        return ResponseResult.fail();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult exception(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.error("来源URL：{}，缺失必填参数：{}", request.getRequestURL(), ex.getLocalizedMessage());
        return ResponseResult.fail(String.format("缺失必填参数：%s", ex.getLocalizedMessage()), ResponseCodeEnum.PARAM_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult exception(MethodArgumentNotValidException exception, HttpServletRequest request) {
        try {
            StringBuilder errorInfo = new StringBuilder();
            BindingResult bindingResult = exception.getBindingResult();
            for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
                if (i > 0) {
                    errorInfo.append(";");
                }
                FieldError fieldError = bindingResult.getFieldErrors().get(i);
                errorInfo.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
            }
            return ResponseResult.fail("方法参数无效：" + errorInfo.toString(), ResponseCodeEnum.PARAM_ERROR);
        } catch (Exception e) {
            log.error("来源URL：{}", request.getRequestURL());
            log.error("方法参数无效：{}", e);
            return ResponseResult.failMsg("方法参数无效：" + e.getMessage());
        }

    }

    @ExceptionHandler
    public ResponseResult exception(Exception ex, HttpServletRequest request) {
        log.error("来源URL：{}，异常信息:{}", request.getRequestURL(), ex.toString());
        return ResponseResult.fail();
    }
}