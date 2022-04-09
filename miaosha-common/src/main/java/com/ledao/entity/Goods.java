package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品实体类
 *
 * @author LeDao
 * @company
 * @create 2022-04-01 17:59
 */
@Data
@TableName("t_goods")
public class Goods implements Serializable {

    /**
     * 编号
     */
    @TableId
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private Double price;
    /**
     * 图片
     */
    private String image;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 详情
     */
    private String detail;

    private static final long serialVersionUID = 1L;
}