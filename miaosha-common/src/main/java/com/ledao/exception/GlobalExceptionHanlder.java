package com.ledao.exception;

import com.ledao.entity.PR;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author LeDao
 * @company
 * @create 2022-03-31 0:18
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(value = Exception.class)
    public PR exceptionHandler(HttpServletRequest request, Exception e) {
        return PR.error("服务端异常，请联系管理员" + "<br/>" + e.getMessage() + "<br/>" + e.getStackTrace());
    }
}
