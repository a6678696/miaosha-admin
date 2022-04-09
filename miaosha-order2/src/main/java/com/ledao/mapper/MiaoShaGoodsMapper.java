package com.ledao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ledao.entity.MiaoShaGoods;

import java.util.List;

/**
 * @author LeDao
 * @company
 * @create 2022-04-01 18:31
 */
public interface MiaoShaGoodsMapper extends BaseMapper<MiaoShaGoods> {

    /**
     * 获取正在秒杀的商品
     *
     * @return
     */
    List<MiaoShaGoods> listAllNow();

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    MiaoShaGoods findById(Integer id);

    /**
     * 减秒杀商品的数量-1
     *
     * @param id
     * @return
     */
    int reduceStock(Integer id);
}