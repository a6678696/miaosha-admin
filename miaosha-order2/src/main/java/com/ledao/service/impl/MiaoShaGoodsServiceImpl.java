package com.ledao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.ledao.entity.MiaoShaGoods;
import com.ledao.mapper.MiaoShaGoodsMapper;
import com.ledao.service.MiaoShaGoodsService;
import com.ledao.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LeDao
 * @company
 * @create 2022-04-01 22:04
 */
@Service("miaoShaGoodsService")
public class MiaoShaGoodsServiceImpl extends ServiceImpl<MiaoShaGoodsMapper, MiaoShaGoods> implements MiaoShaGoodsService {

    @Resource
    private MiaoShaGoodsMapper miaoShaGoodsMapper;

    @Override
    public List<MiaoShaGoods> listAllNow() {
        return miaoShaGoodsMapper.listAllNow();
    }

    @Override
    public MiaoShaGoods findById(Integer id) {
        Gson gson = new Gson();
        String key = "miaoShaGoods_" + id;
        MiaoShaGoods miaoShaGoods;
        //当这个秒杀商品在Redis中存在时
        if (RedisUtil.existKey(key)) {
            miaoShaGoods = gson.fromJson(RedisUtil.getKeyValue(key), MiaoShaGoods.class);
        } else {
            miaoShaGoods = miaoShaGoodsMapper.findById(id);
            RedisUtil.setKey(key, gson.toJson(miaoShaGoods));
            //获取秒杀时长
            long time = (miaoShaGoods.getEndTime().getTime() - System.currentTimeMillis()) / 1000;
            //设置过期时间
            RedisUtil.setKeyTime(key, time);
        }
        return miaoShaGoods;
    }
}
