package com.ledao.util;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author LeDao
 * @company
 * @create 2022-03-30 1:29
 */
public class UUIDUtil {

    public static String genUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(genUuid());
    }
}
