package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 *
 * @author LeDao
 * @company
 * @create 2022-04-02 22:34
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    /**
     * 编号
     */
    @TableId
    private Integer id;
    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;
    /**
     * 商品id
     */
    @TableField(value = "goodsId")
    private Integer goodsId;
    /**
     * 对应的商品实体类
     */
    @TableField(exist = false)
    private Goods goods;
    /**
     * 秒杀商品id
     */
    @TableField(value = "miaoShaGoodsId")
    private Integer miaoShaGoodsId;
    /**
     * 支付状态,0代表未支付,1代表已支付,2代表已取消
     */
    @TableField(value = "payStatus")
    private Integer payStatus;
    /**
     * 购买数量
     */
    private Integer num;
    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userId;
    /**
     * 价格
     */
    private double price;

    private static final long serialVersionUID = 1L;
}