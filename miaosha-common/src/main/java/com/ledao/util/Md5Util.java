package com.ledao.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author LeDao
 * @company
 * @create 2022-03-29 23:11
 */
public class Md5Util {

    /**
     * 前端加密盐
     */
    private static final String FRONT_SALT = "2das8fse5hgb";
    /**
     * 后端加密前置盐
     */
    private static final String BACK_SALT_BEFORE = "9das1wda5dsa";
    /**
     * 后端加密后置盐
     */
    private static final String BACK_SALT_AFTER = "4dsa0kjh6ert";

    /**
     * md5加密
     *
     * @param data
     * @return
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * 前端加盐后md5加密
     *
     * @param frontData
     * @return
     */
    public static String frontMd5(String frontData) {
        return md5(FRONT_SALT + frontData);
    }

    /**
     * 后端加盐后md5加密
     *
     * @param backData
     * @return
     */
    public static String backMd5(String backData) {
        return md5(BACK_SALT_BEFORE + backData + BACK_SALT_AFTER);
    }

    public static void main(String[] args) {
        System.out.println("前端md5加密：" + frontMd5("4"));
        System.out.println("后端md5加密验证：" + backMd5(frontMd5("4")));
    }
}
