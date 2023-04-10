/**
 * 文件名：BookFriendTalkService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.xiaobai.entity.BookFriendTalkModel;
import com.xiaobai.entity.BookUserModel;
import com.xiaobai.mapper.BookFriendTalkModelMapper;
import com.xiaobai.mapper.BookOrderModelMapper;
import com.xiaobai.mapper.BookUserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/10 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookFriendTalkService {
    @Autowired
    private BookFriendTalkModelMapper bookFriendTalkModelMapper;

    @Autowired
    private BookOrderModelMapper bookOrderModelMapper;

    @Autowired
    private BookUserModelMapper bookUserModelMapper;

    public String getFriendTalk(Long account){
        List<String> bookNameFromOrderTable = bookOrderModelMapper.getBookNameFromOrderTable(account);
        if(bookNameFromOrderTable!=null&&bookNameFromOrderTable.size()!=0){
            List<BookFriendTalkModel> talk = bookFriendTalkModelMapper.getTalk(bookNameFromOrderTable);
            if(talk!=null&&talk.size()!=0){
                StringBuilder stringBuilder = new StringBuilder();
                for(int index = 0;index<talk.size();index++){
                    if(index<talk.size()-1) {
                        stringBuilder.append(talk.get(index).getAccount());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getUserName());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getBookName());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getTalk());
                        stringBuilder.append("^");
                    }else{
                        stringBuilder.append(talk.get(index).getAccount());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getUserName());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getBookName());
                        stringBuilder.append("^");
                        stringBuilder.append(talk.get(index).getTalk());
                    }
                }
                return stringBuilder.toString();
            }else{
                return "empty";
            }
        }else{
            return "false";
        }
    }

    /**
     * 获得当前用户租借过的书籍，以及是否书评
     * @param account
     * @return
     */
    public String getBookNameAndBooleanTalk(Long account){
        bookOrderModelMapper.createBookOrderBookNameList(account);
        List<String> bookNameInBookOrderNameList = bookOrderModelMapper.getBookNameInBookOrderNameList();
        List<Integer> booleanTalkInBookOrderNameList = bookOrderModelMapper.getBooleanTalkInBookOrderNameList();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<bookNameInBookOrderNameList.size();index++){
            if(index<bookNameInBookOrderNameList.size()-1) {
                stringBuilder.append(bookNameInBookOrderNameList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booleanTalkInBookOrderNameList.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(bookNameInBookOrderNameList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booleanTalkInBookOrderNameList.get(index));
            }
        }
        bookOrderModelMapper.clearTableBookOrderBookName();
        return stringBuilder.toString();
    }

    /**
     * 增加新书评
     * @param account
     * @param bookName
     * @param talk
     * @return
     */
    public boolean addTalk(Long account,String bookName,String talk){
        //对订单表中的书籍是否评论进行更新
        bookOrderModelMapper.uploadBookBooleanTalk(account,bookName);
        //获取用户昵称
        BookUserModel bookUserModel = new BookUserModel();
        bookUserModel.setAccount(account);
        String name = bookUserModelMapper.getName(bookUserModel);
        //对书评表进行更新
        BookFriendTalkModel bookFriendTalkModel = new BookFriendTalkModel();
        bookFriendTalkModel.setAccount(account);
        bookFriendTalkModel.setBookName(bookName);
        bookFriendTalkModel.setTalk(talk);
        bookFriendTalkModel.setUserName(name);
        //获取发生时间
        Date dt =new Date();
        bookFriendTalkModel.setGenerateTime(dt);
        bookFriendTalkModelMapper.addNewTalk(bookFriendTalkModel);
        return true;
    }

    /**
     * 删除数据
     * @param bookFriendTalkModel
     * @return
     */
    public boolean deleteTalk(BookFriendTalkModel bookFriendTalkModel){
        bookFriendTalkModelMapper.deleteTalk(bookFriendTalkModel);
        return true;
    }
}
