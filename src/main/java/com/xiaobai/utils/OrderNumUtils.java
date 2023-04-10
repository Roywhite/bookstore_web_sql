/**
 * 文件名：OrderNumUtils
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：用于生成唯一订单工具类
 */

package com.xiaobai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/8 16:38
 */
public class OrderNumUtils {

    /**
     * 生成订单
     * @param account
     * @return
     */
    public static Long getOrderNum(Long account) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            result += random.nextInt(10);
        }
        String orderNumString = newDate + String.valueOf(account) + result;
        return Long.parseLong(orderNumString);
    }

}
