package com.xiaobai.mapper;

import com.xiaobai.entity.BookBooksModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookBooksModelMapper {
    /**
     * 先将BOOK_BOOKS按书名进行分组计算平均数，然后按照平均数大小进行排序，然后将查到的结果取前十插入到BOOK_RANKING_LIST表
     */
    void createBookRankingList();

    /**
     * 清空排行榜
     */
    void clearBookRankingList();

    /**
     * 获取推荐表里书的名字（已排好序）
     * @return
     */
    List<String> getBookNames();

    /**
     * 获取推荐表图书对应的分数
     * @return
     */
    List<Double> getBookScore();

    /**
     * 将查询结果存入search表
     * @param bookNameFromIntent
     */
    void createBookSearchList(@Param("bookNameFromIntent") String bookNameFromIntent);

    /**
     * 清空搜索表
     */
    void clearBookSearch();

    /**
     * 从搜索表里获取书名
     * @return
     */
    List<String> getBookNameListFromSearch();

    /**
     * 从搜索表里获取店名
     * @return
     */
    List<String> getLocationNameListFromSearch();

    /**
     * 从搜索框获取地址
     * @return
     */
    List<String> getLocationFullNameListFromSearch();

    /**
     * 从搜索结果获取暂存数量
     * @return
     */
    List<Integer> getBooksNumListFromSearch();

    /**
     * 查询书本简介
     * @param bookName
     * @param bookStore
     * @return
     */
    String getBooksProfile(@Param("bookname") String bookName,@Param("bookstore") String bookStore);

    /**
     * 通过前台传来的字符串和店面查询书名和数量，然后插入查询表（已弃用）
     * @param text
     * @param bookStore
     */
    void createBookSearchWebListByTextAndBookStore(@Param("bookNameFromWeb") String text,@Param("bookStore") String bookStore);

    /**
     * 从search_web表里搜索书名（已弃用）
     * @return
     */
    List<String> getBookNameFromTableWeb();

    /**
     * 从search_web表里搜索数量（已弃用）
     * @return
     */
    List<Integer> getBookNumFromTableWeb();

    /**
     * 清理搜索结果表（已弃用）
     */
    void clearBookSearchWeb();

    /**
     * 根据传入值和书店查出相关内容到新表
     * @param text
     * @param bookStore
     */
    void createRentBookSearchListByTextAndBookStore(@Param("bookNameText") String text,@Param("bookStore") String bookStore);

    /**
     * 从借书搜索表取出书名
     * @return
     */
    List<String> getBookRentNameByRent();

    /**
     * 从借书搜索表取出店名
     * @return
     */
    List<String> getLocationNameByRent();

    /**
     * 从借书搜索表取出货架
     * @return
     */
    List<String> getBooksLocationByRent();

    /**
     * 从借书搜索表取出数量
     * @return
     */
    List<Integer> getBooksNumByRent();

    /**
     * 清空租借搜索暂存表
     */
    void deleteBookRentSearch();

    /**
     * 通过书店和书名获取当前书店当前数的当前数量
     * @param bookStore
     * @param BooksName
     * @return
     */
    Integer getBookNowNum(@Param("bookStore") String bookStore,@Param("bookName") String BooksName);

    /**
     * 更新该书店该数的数量
     * @param bookStore
     * @param BooksName
     * @param bookNowNum
     */
    void updateBooksNum(@Param("bookStore") String bookStore,@Param("bookName") String BooksName,@Param("bookNowNum") Long bookNowNum);

    /**
     * 获得书籍编码
     * @param bookName
     * @param bookStore
     * @return
     */
    Long getBookCoding(@Param("bookName") String bookName,@Param("bookStore") String bookStore);

    /**
     * 通过书店和书名找到该书并更新剩余数量
     * @param bookStore
     * @param bookName
     * @param bookNum
     */
    void updateBookStoreBookNumByRetrun(@Param("bookStore") String bookStore,@Param("bookName") String bookName,@Param("bookNum") Integer bookNum);

    /**
     * 获得该书租借次数
     * @param bookName
     * @param bookStore
     * @return
     */
    Integer getRentNum(@Param("bookName") String bookName,@Param("bookStore") String bookStore);

    /**
     * 获得归还时书籍的分数
     * @param bookName
     * @param bookStore
     * @return
     */
    Double getBookScoreSingle(@Param("bookName") String bookName,@Param("bookStore") String bookStore);

    /**
     * 更新次数和分数
     * @param rentNum
     * @param bookScore
     * @param bookName
     * @param bookStore
     */
    void updateBookScoreAndRentNum(@Param("rentNum") int rentNum,@Param("bookScore") double bookScore,@Param("bookName") String bookName,@Param("bookStore") String bookStore);
}