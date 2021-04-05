package com.nia.wj.utils;

import java.util.Random;

/**
 * @program: wj
 * @description: 工具类
 * @author: ganwei
 * @create: 2021-04-05 14:13
 **/
public class StringUtils {

    /**
     * 生成指定长度随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
