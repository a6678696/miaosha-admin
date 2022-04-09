package com.ledao.rabbitmq.impl;

import com.google.gson.Gson;
import com.ledao.config.RabbitMQConfig;
import com.ledao.entity.MiaoShaGoods;
import com.ledao.entity.Order;
import com.ledao.entity.vo.OrderVo;
import com.ledao.rabbitmq.RabbitMQConsumerService;
import com.ledao.service.MiaoShaGoodsService;
import com.ledao.service.OrderService;
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
    private OrderService orderService;

    @Override
    @RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE})
    public void handleMiaoShaRequst(String message) {
        System.out.println(message+": 消费者1");
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(message, OrderVo.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", orderVo.getUserId());
        map.put("miaoShaGoodsId", orderVo.getMiaoShaGoodsId());
        MiaoShaGoods miaoShaGoods = miaoShaGoodsService.findById(orderVo.getMiaoShaGoodsId());
        //判断是否已经过了秒杀时间,时间过了不可以秒杀
        if (miaoShaGoods.getEndTime().getTime() - System.currentTimeMillis() >= 0) {
            //判断秒杀商品的数量是否大于0,不大于0不可以秒杀
            if (miaoShaGoods.getStock() > 0) {
                //判断用户是否重复秒杀,已经秒杀过不能秒杀
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
    }
}
