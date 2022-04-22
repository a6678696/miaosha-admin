package com.ledao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ledao.entity.Goods;

/**
 * 商品Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-04-01 18:06
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    Goods findById(Integer id);

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    int update(Goods goods);
}
