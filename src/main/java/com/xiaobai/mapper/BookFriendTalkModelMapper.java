package com.xiaobai.mapper;

import com.xiaobai.entity.BookFriendTalkModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BookFriendTalkModelMapper {

    /**
     * 获取用户租借过的书籍的评论
     * @param bookNameFromOrderTable
     * @return
     */
    List<BookFriendTalkModel> getTalk(@Param("bookNameFromOrderTable") List<String> bookNameFromOrderTable);

    /**
     * 在书评表里插入新的书评
     * @param bookFriendTalkModel
     */
    void addNewTalk(BookFriendTalkModel bookFriendTalkModel);

    /**
     * 通过account、bookName、talk删除数据
     * @param bookFriendTalkModel
     */
    void deleteTalk(BookFriendTalkModel bookFriendTalkModel);
}