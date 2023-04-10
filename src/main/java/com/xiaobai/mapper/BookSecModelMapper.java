package com.xiaobai.mapper;

import com.xiaobai.entity.BookSecModel;

public interface BookSecModelMapper {
    /**
     * 获取加密解密的秘钥
     * @return
     */
    String getSec();
}