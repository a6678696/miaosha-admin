package com.ledao.util;

import com.google.gson.Gson;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author LeDao
 * @company
 * @create 2022-03-30 2:22
 */
@Component
public class RedisUtil {

    /**
     * 维护一个本类的静态变量
     */
    private static RedisUtil redisUtil;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 使用@PostConstruct注解标记工具类,初始化Bean
     */
    @PostConstruct
    public void init() {
        redisUtil = this;
        redisUtil.stringRedisTemplate = this.stringRedisTemplate;
    }

    /**
     * Java实体转化为json
     *
     * @param o
     * @return
     */
    public static String entityToJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    /**
     * json转化为Java实体
     *
     * @param json
     * @param o
     * @return
     */
    public static Object jsonToEntity(String json, Object o) {
        Gson gson = new Gson();
        o = gson.fromJson(json, o.getClass());
        return o;
    }

    /**
     * 设置key
     *
     * @param key
     * @param value
     * @return
     */
    public static void setKey(String key, String value) {
        redisUtil.stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取key对应的值
     *
     * @param key
     * @return
     */
    public static String getKeyValue(String key) {
        return redisUtil.stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    public static boolean existKey(String key) {
        return Boolean.TRUE.equals(redisUtil.stringRedisTemplate.hasKey(key));
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public static boolean delKey(String key) {
        boolean result = false;
        //key存在就删除
        if (existKey(key)) {
            result = Boolean.TRUE.equals(redisUtil.stringRedisTemplate.delete(key));
        }
        return result;
    }

    /**
     * 从右边添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public static void listRightPush(String key, String value) {
        redisUtil.stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 从右边弹出元素
     *
     * @param key
     * @return
     */
    public static void listRightPop(String key) {
        redisUtil.stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取list的元素个数
     *
     * @param key
     * @return
     */
    public static Long listLength(String key) {
        return redisUtil.stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 获取元素集合(包含头包含尾)
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> listRange(String key, Long start, Long end) {
        return redisUtil.stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 给指定key设置过期时间(秒)
     *
     * @param key
     * @param seconds
     */
    public static void setKeyTime(String key, Long seconds) {
        redisUtil.stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 获取指定key的过期时间(秒)
     *
     * @param key
     * @return
     */
    public static Long getKeyTime(String key) {
        return redisUtil.stringRedisTemplate.getExpire(key);
    }
}
