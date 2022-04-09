package com.ledao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledao.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @author LeDao
 * @company
 * @create 2022-04-02 22:39
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据条件获取订单
     *
     * @param map
     * @return
     */
    List<Order> list(Map<String, Object> map);

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 根据id查找订单
     *
     * @param id
     * @return
     */
    Order findById(Integer id);

    /**
     * 根据用户id和秒杀商品id获取订单(用于判断用户是否重复秒杀)
     *
     * @param map
     * @return
     */
    Order findByUserIdAndMiaoShaGoodsId(Map<String, Object> map);
}
