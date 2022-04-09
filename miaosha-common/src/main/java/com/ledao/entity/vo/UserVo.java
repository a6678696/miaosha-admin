package com.ledao.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户Vo实体类
 *
 * @author LeDao
 * @company
 * @create 2022-03-29 22:48
 */
@Data
public class UserVo implements Serializable {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
}
