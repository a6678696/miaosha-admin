package com.ledao.interceptor;

import com.ledao.util.RedisUtil;
import com.ledao.util.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 *
 * @author LeDao
 * @company
 * @create 2022-03-30 23:09
 */
public class SysInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token)) {
                throw new RuntimeException("签名验证不存在");
            } else {
                boolean key = RedisUtil.existKey(token);
                if (key) {
                    return true;
                } else {
                    throw new RuntimeException("签名失败");
                }
            }
        } else {
            return true;
        }
    }
}
