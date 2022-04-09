package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品实体类
 *
 * @author LeDao
 * @company
 * @create 2022-04-01 18:31
 */
@Data
@TableName("t_miaosha_goods")
public class MiaoShaGoods implements Serializable {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 真实商品id
     */
    @TableField(value = "goodsId")
    private Integer goodsId;
    /**
     * 商品实体,获取对应商品的信息
     */
    @TableField(exist = false)
    private Goods goods;
    /**
     * 秒杀的价格
     */
    private Double price;
    /**
     * 秒杀的数量
     */
    private Integer stock;
    /**
     * 开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    private static final long serialVersionUID = 1L;
}