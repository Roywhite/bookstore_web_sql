/**
 * 文件名：BookLocationService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.xiaobai.mapper.BookLocationModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/8 03:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookLocationService {
    @Autowired
    private BookLocationModelMapper bookLocationModelMapper;

    /**
     * 获得除了网络店外的所有线下店List
     * @return
     */
    public String getBookLocationExceptNet(){
        List<String> bookStoreExceptNET = bookLocationModelMapper.getBookStoreExceptNET();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<bookStoreExceptNET.size();index++){
            if(index<bookStoreExceptNET.size()-1) {
                stringBuilder.append(bookStoreExceptNET.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(bookStoreExceptNET.get(index));
            }
        }

        return stringBuilder.toString();
    }
}
