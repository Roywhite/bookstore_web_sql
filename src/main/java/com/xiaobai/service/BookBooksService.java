/**
 * 文件名：BookBooksService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.xiaobai.mapper.BookBooksModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/4/27 03:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookBooksService {
    @Autowired
    private BookBooksModelMapper bookBooksModelMapper;

    /**
     * 获取排行榜数据
     * @return
     */
    public String getBookRankingList(){
        //先将结果排序，然后取前十插入排行榜
        bookBooksModelMapper.createBookRankingList();
        //从排行榜中获取数据，因为数据一定是大于10条的，所以不需要进行判空
        List<String> bookNames = bookBooksModelMapper.getBookNames();
        List<Double> bookScore = bookBooksModelMapper.getBookScore();
        String text = "";
        for(int index = 0;index<bookNames.size();index++){
            if(index<bookNames.size()-1) {
                text = text + bookNames.get(index) + "~" + bookScore.get(index) + "~";
            }else{
                text = text + bookNames.get(index) + "~" + bookScore.get(index);
            }
        }
        //清空排行表
        bookBooksModelMapper.clearBookRankingList();
        return text;
    }

    /**
     * 获取搜索结果
     * @param bookName
     * @return
     */
    public String getSearchList(String bookName){
        //将查询结果插入搜索暂存表
        bookBooksModelMapper.createBookSearchList(bookName);
        //获取对应的数据
        List<String> locationNameListFromSearch = bookBooksModelMapper.getLocationNameListFromSearch();
        List<String> bookNameListFromSearch = bookBooksModelMapper.getBookNameListFromSearch();
        List<String> locationFullNameListFromSearch = bookBooksModelMapper.getLocationFullNameListFromSearch();
        List<Integer> booksNumListFromSearch = bookBooksModelMapper.getBooksNumListFromSearch();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<locationNameListFromSearch.size();index++){
            if(index<locationNameListFromSearch.size()-1) {
                stringBuilder.append(bookNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationFullNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksNumListFromSearch.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(bookNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationFullNameListFromSearch.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksNumListFromSearch.get(index));
            }
        }
        //清空搜索表
        bookBooksModelMapper.clearBookSearch();
        return stringBuilder.toString();
    }




    /**
     * 将前台关键字和店面传入获取结果（已弃用）
     * @param text
     * @param bookStore
     * @return
     */
    public String getBookNameAndNumInWeb(String text,String bookStore){
        //将查询结果插入搜索暂存表
        bookBooksModelMapper.createBookSearchWebListByTextAndBookStore(text,bookStore);
        //获取对应的数据
        List<String> bookNameFromTableWeb = bookBooksModelMapper.getBookNameFromTableWeb();
        List<Integer> bookNumFromTableWeb = bookBooksModelMapper.getBookNumFromTableWeb();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<bookNameFromTableWeb.size();index++){
            if(index<bookNameFromTableWeb.size()-1) {
                stringBuilder.append(bookNameFromTableWeb.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookNumFromTableWeb.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(bookNameFromTableWeb.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookNumFromTableWeb.get(index));
            }
        }
        //清空搜索表
        bookBooksModelMapper.clearBookSearchWeb();
        return stringBuilder.toString();
    }

    /**
     * 根据前台传入的字符和店面查找出对应的书籍信息
     * @param text
     * @param bookStore
     * @return
     */
    public String getRentBookSearch(String text,String bookStore){
        bookBooksModelMapper.createRentBookSearchListByTextAndBookStore(text,bookStore);
        //获取对应的数据
        List<String> bookRentNameByRent = bookBooksModelMapper.getBookRentNameByRent();
        List<String> locationNameByRent = bookBooksModelMapper.getLocationNameByRent();
        List<String> booksLocationByRent = bookBooksModelMapper.getBooksLocationByRent();
        List<Integer> booksNumByRent = bookBooksModelMapper.getBooksNumByRent();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<bookRentNameByRent.size();index++){
            if(index<bookRentNameByRent.size()-1) {
                stringBuilder.append(bookRentNameByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationNameByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksLocationByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksNumByRent.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(bookRentNameByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(locationNameByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksLocationByRent.get(index));
                stringBuilder.append("^");
                stringBuilder.append(booksNumByRent.get(index));
            }
        }
        //清空搜索表
        bookBooksModelMapper.deleteBookRentSearch();
        return stringBuilder.toString();
    }
}
