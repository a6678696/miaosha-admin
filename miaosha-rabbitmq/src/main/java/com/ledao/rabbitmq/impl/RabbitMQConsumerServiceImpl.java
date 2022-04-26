package com.ledao.rabbitmq.impl;

import com.google.gson.Gson;
import com.ledao.config.RabbitMQConfig;
import com.ledao.entity.Goods;
import com.ledao.entity.MiaoShaGoods;
import com.ledao.entity.Order;
import com.ledao.entity.vo.OrderVo;
import com.ledao.rabbitmq.RabbitMQConsumerService;
import com.ledao.service.GoodsService;
import com.ledao.service.MiaoShaGoodsService;
import com.ledao.service.OrderService;
import com.ledao.util.RedisUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQService消费者接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-04-07 1:08
 */
@Service("rabbitMQConsumerService")
public class RabbitMQConsumerServiceImpl implements RabbitMQConsumerService {

    @Resource
    private MiaoShaGoodsService miaoShaGoodsService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Override
    @RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE})
    public void handleMiaoShaRequst(String message) {
        System.out.println(message + ": 消费者1");
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(message, OrderVo.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", orderVo.getUserId());
        map.put("miaoShaGoodsId", orderVo.getMiaoShaGoodsId());
        MiaoShaGoods miaoShaGoods = miaoShaGoodsService.findById(orderVo.getMiaoShaGoodsId());
        //判断秒杀商品的数量是否大于0,不大于0不可以秒杀
        if (miaoShaGoods.getStock() > 0) {
            //判断用户是否重复秒杀,已经秒杀过不能秒杀,即使订单因为30分钟未支付被取消了也不能重新秒杀
            if (orderService.findByUserIdAndMiaoShaGoodsId(map) == null) {
                Order order = new Order();
                order.setGoodsId(orderVo.getGoodsId());
                order.setUserId(orderVo.getUserId());
                order.setPrice(orderVo.getPrice());
                order.setMiaoShaGoodsId(orderVo.getMiaoShaGoodsId());
                order.setPayStatus(0);
                order.setNum(1);
                orderService.add(order);
            }
        }
    }

    @Override
    @RabbitListener(queues = {RabbitMQConfig.DELAYED_QUEUE})
    public void receiveMessageDelayed(String message) {
        int orderId = Integer.parseInt(message.split("_")[1]);
        Order order = orderService.findById(orderId);
        if (order.getPayStatus() == 0) {
            //将状态设置为已取消
            order.setPayStatus(2);
            orderService.update(order);
            //恢复商品的库存
            Goods goods = goodsService.findById(order.getGoodsId());
            goods.setStock(goods.getStock() + order.getNum());
            goodsService.update(goods);
            MiaoShaGoods miaoShaGoods = miaoShaGoodsService.findById(order.getMiaoShaGoodsId());
            //如果秒杀时间还没过,释放秒杀商品库存
            if (miaoShaGoods.getEndTime().getTime() - System.currentTimeMillis() >= 0) {
                miaoShaGoods.setStock(miaoShaGoods.getStock() + order.getNum());
                miaoShaGoodsService.update(miaoShaGoods);
                Gson gson = new Gson();
                String key = "miaoShaGoods_" + order.getMiaoShaGoodsId();
                miaoShaGoods.setStock(miaoShaGoods.getStock());
                RedisUtil.setKey(key, gson.toJson(miaoShaGoods));
                //获取秒杀时长
                long time = (miaoShaGoods.getEndTime().getTime() - System.currentTimeMillis()) / 1000;
                //设置过期时间
                RedisUtil.setKeyTime(key, time);
            }
        }
    }
}
