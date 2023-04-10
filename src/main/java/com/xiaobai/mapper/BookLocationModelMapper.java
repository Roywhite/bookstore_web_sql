package com.xiaobai.mapper;

import com.xiaobai.entity.BookLocationModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookLocationModelMapper {

    /**
     * 获取详细地址
     * @param bookStore
     * @return
     */
    String getLocationFull(@Param("bookstore") String bookStore);

    /**
     * 查找出除了NET外的所有线下店
     * @return
     */
    List<String> getBookStoreExceptNET();
}