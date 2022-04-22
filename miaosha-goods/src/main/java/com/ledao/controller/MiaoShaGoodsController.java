package com.ledao.controller;

import com.ledao.entity.MiaoShaGoods;
import com.ledao.entity.Order;
import com.ledao.service.MiaoShaGoodsService;
import com.ledao.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author LeDao
 * @company
 * @create 2022-04-01 22:33
 */
@RestController
@RequestMapping("/miaoShaGoods")
public class MiaoShaGoodsController {

    @Resource
    private MiaoShaGoodsService miaoShaGoodsService;

    @Resource
    private OrderService orderService;

    @RequestMapping("/listAllNow")
    public List<MiaoShaGoods> listAllNow(HttpServletRequest request) {
        List<MiaoShaGoods> miaoShaGoodsList=miaoShaGoodsService.listAllNow();
        int userId = Integer.parseInt(request.getHeader("userId"));
        //已经抢到的秒杀商品不能查看
        for (int i = 0; i < miaoShaGoodsList.size(); i++) {
            MiaoShaGoods miaoShaGoods = miaoShaGoodsList.get(i);
            Map<String, Object> map = new HashMap<>(16);
            map.put("userId", userId);
            map.put("miaoShaGoodsId", miaoShaGoods.getId());
            Order order = orderService.findByUserIdAndMiaoShaGoodsId(map);
            //已经秒杀过就不显示这个商品
            if (order != null) {
                miaoShaGoodsList.remove(miaoShaGoods);
                i--;
            }
        }
        for (MiaoShaGoods miaoShaGoods : miaoShaGoodsList) {
            System.out.println(miaoShaGoods.getGoods().getName());
        }
        return miaoShaGoodsList;
    }

    @RequestMapping("/findById")
    public MiaoShaGoods findById(Integer id) {
        return miaoShaGoodsService.findById(id);
    }
}
