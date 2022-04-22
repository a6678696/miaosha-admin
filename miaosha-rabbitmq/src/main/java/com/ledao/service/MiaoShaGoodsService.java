package com.ledao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledao.entity.MiaoShaGoods;

import java.util.List;

/**
 * @author LeDao
 * @company
 * @create 2022-04-01 22:02
 */
public interface MiaoShaGoodsService extends IService<MiaoShaGoods> {

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
     * 修改秒杀商品
     *
     * @param miaoShaGoods
     * @return
     */
    int update(MiaoShaGoods miaoShaGoods);
}
