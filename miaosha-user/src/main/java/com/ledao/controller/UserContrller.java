package com.ledao.controller;

import cn.hutool.core.io.file.FileAppender;
import com.ledao.entity.User;
import com.ledao.entity.vo.UserVo;
import com.ledao.service.UserService;
import com.ledao.util.Md5Util;
import com.ledao.util.RedisUtil;
import com.ledao.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LeDao
 * @company
 * @create 2022-03-29 16:50
 */
@RestController
@RequestMapping("/user")
public class UserContrller {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(UserVo userVo) {
        Map<String, Object> resultMap = new HashMap<>(16);
        User user = userService.findByUserName(userVo.getUserName());
        if (user != null && user.getPassword().equals(Md5Util.backMd5(userVo.getPassword()))) {
            String token = "tk" + UUIDUtil.genUuid();
            RedisUtil.setKey(token, RedisUtil.entityToJson(user));
            RedisUtil.setKeyTime(token, (long) (30 * 60));
            resultMap.put("user", user);
            resultMap.put("token", token);
            return resultMap;
        }
        return null;
    }

    /**
     * 用户注册时检验用户名是否已经存在于数据库中
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/checkUserName")
    public boolean checkUserName(UserVo userVo) {
        User user = userService.findByUserName(userVo.getUserName());
        return user == null;
    }

    /**
     * 用户注册时检验用户名是否已经存在于数据库中
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/checkPhone")
    public boolean checkPhone(UserVo userVo) {
        User user = userService.findByPhone(userVo.getPhone());
        return user == null;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public boolean register(User user) {
        user.setRegisterDate(new Date());
        user.setPassword(Md5Util.backMd5(user.getPassword()));
        int key = userService.add(user);
        return key > 0;
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        //从前端获取token
        String token = request.getHeader("token");
        return RedisUtil.delKey(token);
    }

    @RequestMapping("/loginAuto")
    public void loginAuto(){
        File file = new File("C:\\Users\\LeDao\\Desktop\\1.txt");
        for (int i = 1; i <= 1000; i++) {
            FileAppender fileAppender = new FileAppender(file, 16, true);
            User user = userService.findByUserName("user" + i);
            String token = "tk" + UUIDUtil.genUuid();
            RedisUtil.setKey(token, RedisUtil.entityToJson(user));
            RedisUtil.setKeyTime(token, (long) (60 * 60 * 24));
            fileAppender.append(user.getId() + "," + token);
            fileAppender.flush();
        }
    }
}
