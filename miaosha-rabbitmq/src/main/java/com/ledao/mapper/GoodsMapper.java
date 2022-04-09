package com.ledao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledao.entity.Goods;

/**
 * 商品Mapper接口
 *
 * @author LeDao
 * @company
 * @create 2022-04-01 17:59
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    Goods findById(Integer id);

    /**
     * 减秒杀商品的数量-1
     *
     * @param id
     * @return
     */
    int reduceStock(Integer id);
}