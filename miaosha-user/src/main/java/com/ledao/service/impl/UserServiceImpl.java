package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledao.entity.User;
import com.ledao.mapper.UserMapper;
import com.ledao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-03-29 16:48
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userName", userName);
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    public User findByPhone(String phone) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone);
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    public Integer add(User user) {
        return userMapper.insert(user);
    }
}
