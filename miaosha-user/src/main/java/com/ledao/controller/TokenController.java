package com.ledao.controller;

import com.ledao.util.RedisUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Token控制层
 *
 * @author LeDao
 * @company
 * @create 2022-03-30 3:20
 */
@RestController
@RequestMapping("/user/token")
public class TokenController {

    /**
     * 刷新token保持登录状态
     *
     * @param request
     * @return
     */
    @GetMapping("/refreshToken")
    public boolean refreshToken(HttpServletRequest request) {
        //从前端获取token
        String token = request.getHeader("token");
        //将token的过期时间重新设置为30分钟
        RedisUtil.setKeyTime(token, (long) (30 * 60));
        return RedisUtil.existKey(token);
    }
}
