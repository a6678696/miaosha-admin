package com.ledao.controller;

import com.ledao.entity.MiaoShaGoods;
import com.ledao.entity.Order;
import com.ledao.entity.vo.OrderVo;
import com.ledao.feign.RabbitMQFeignService;
import com.ledao.service.MiaoShaGoodsService;
import com.ledao.service.OrderService;
import com.ledao.util.RedisUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LeDao
 * @company
 * @create 2022-04-02 22:41
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private MiaoShaGoodsService miaoShaGoodsService;

    @Resource
    private RabbitMQFeignService rabbitMQFeignService;

    /**
     * 根据条件获取订单
     *
     * @param orderVo
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(OrderVo orderVo) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", orderVo.getUserId());
        resultMap.put("rows", orderService.list(map));
        return resultMap;
    }

    /**
     * 添加或修改订单
     *
     * @param orderVo
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(OrderVo orderVo) {
        Map<String, Object> resultMap = new HashMap<>(16);
        int key = 0;
        if (orderVo.getId() == null) {
            System.out.println("------------开始秒杀-----------");
            MiaoShaGoods miaoShaGoods = miaoShaGoodsService.findById(orderVo.getMiaoShaGoodsId());
            //判断是否已经过了秒杀时间,时间过了不可以秒杀
            if (miaoShaGoods.getEndTime().getTime() - System.currentTimeMillis() >= 0) {
                //当前秒杀商品的库存大于0时才放进消息队列
                if (miaoShaGoods.getStock() > 0) {
                    rabbitMQFeignService.sendInformation(RedisUtil.entityToJson(orderVo));
                    key = 1;
                } else {
                    resultMap.put("errorInfo", "你手慢了,该商品已经被秒杀完了!!");
                }
            }
        } else {
            Order order = orderService.findById(orderVo.getId());
            order.setPayStatus(orderVo.getPayStatus());
            key = orderService.update(order);
        }
        if (key > 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 检验是否秒杀成功,返回1代表秒杀成功,返回0代表排队中,返回-1代表秒杀失败
     *
     * @param userId
     * @param miaoShaGoodsId
     * @return
     */
    @RequestMapping("/checkStatus")
    public int checkStatus(int userId, int miaoShaGoodsId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", userId);
        map.put("miaoShaGoodsId", miaoShaGoodsId);
        Order order = orderService.findByUserIdAndMiaoShaGoodsId(map);
        //订单存在,代表秒杀成功
        if (order != null) {
            return 1;
        } else {
            MiaoShaGoods miaoShaGoods = miaoShaGoodsService.findById(miaoShaGoodsId);
            //还有库存,代表在排队中
            if (miaoShaGoods.getStock() > 0) {
                return 0;
            } else {//没有库存,代表秒杀已结束
                return -1;
            }
        }
    }
}
