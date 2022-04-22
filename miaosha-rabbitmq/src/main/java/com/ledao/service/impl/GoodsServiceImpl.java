package com.ledao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ledao.entity.Goods;
import com.ledao.mapper.GoodsMapper;
import com.ledao.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LeDao
 * @company
 * @create 2022-04-01 18:07
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Goods findById(Integer id) {
        return goodsMapper.findById(id);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.updateById(goods);
    }
}
