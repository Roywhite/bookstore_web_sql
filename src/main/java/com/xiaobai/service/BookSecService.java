/**
 * 文件名：BookSecService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.xiaobai.mapper.BookSecModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/4/29 20:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookSecService {
    @Autowired
    private BookSecModelMapper bookSecModelMapper;

    /**
     * 获取加密解密秘钥
     * @return
     */
    public String getSec(){
        String sec = bookSecModelMapper.getSec();
        return sec;
    }
}
