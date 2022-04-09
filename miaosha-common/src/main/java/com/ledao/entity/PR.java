package com.ledao.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 *
 * @author LeDao
 * @company
 * @create 2022-03-31 0:28
 */
public class PR extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public PR() {
        put("code", 0);
    }

    public static PR error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static PR error(String msg) {
        return error(500, msg);
    }

    public static PR error(int code, String msg) {
        PR r = new PR();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static PR ok(String msg) {
        PR r = new PR();
        r.put("msg", msg);
        return r;
    }

    public static PR ok(Map<String, Object> map) {
        PR r = new PR();
        r.putAll(map);
        return r;
    }

    public static PR ok() {
        return new PR();
    }

    @Override
    public PR put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
