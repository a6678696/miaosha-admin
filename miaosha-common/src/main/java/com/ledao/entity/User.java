package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author LeDao
 * @company
 * @create 2022-03-29 16:21
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField(value = "userName")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 注册时间
     */
    @TableField(value = "registerDate")
    private Date registerDate;
    /**
     * 地址
     */
    private String address;
    /**
     * 手机号码
     */
    private String phone;

    private static final long serialVersionUID = 1L;
}
