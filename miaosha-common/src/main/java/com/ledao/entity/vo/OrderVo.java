package com.ledao.entity.vo;

import lombok.Data;

/**
 * @author LeDao
 * @company
 * @create 2022-04-02 22:53
 */
@Data
public class OrderVo {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 秒杀商品id
     */
    private Integer miaoShaGoodsId;
    /**
     * 支付状态,0代表未支付,1代表已支付
     */
    private Integer payStatus;
    /**
     * 价格
     */
    private double price;
}
