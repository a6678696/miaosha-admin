package com.ledao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledao.entity.User;

/**
 * 用户Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-03-29 16:46
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 根据手机号查找用户
     *
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer add(User user);
}
