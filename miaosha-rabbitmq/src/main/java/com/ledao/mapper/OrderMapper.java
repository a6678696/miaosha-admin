package com.ledao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledao.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单Mapper接口实体类
 *
 * @author LeDao
 * @company
 * @create 2022-04-02 22:34
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据条件获取订单
     *
     * @param map
     * @return
     */
    List<Order> list(Map<String, Object> map);

    /**
     * 根据用户id和秒杀商品id获取订单(用于判断用户是否重复秒杀)
     *
     * @param map
     * @return
     */
    Order findByUserIdAndMiaoShaGoodsId(Map<String, Object> map);

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int add(Order order);
}