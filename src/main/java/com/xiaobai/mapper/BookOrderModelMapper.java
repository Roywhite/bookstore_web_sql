package com.xiaobai.mapper;

import com.xiaobai.entity.BookOrderModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookOrderModelMapper {

    /**
     * 通过订单号查询书本名
     * @param currentOrderNum
     * @return
     */
    String getBookName(@Param("currentNum") Long currentOrderNum);

    /**
     * 通过订单号查找订单生成日期
     * @param currentOrderNum
     * @return
     */
    String getTimeGenerate(@Param("currentNum") Long currentOrderNum);

    /**
     * 通过订单号查找续租总时间
     * @param currentOrderNum
     * @return
     */
    Integer getResidueDay(@Param("currentNum") Long currentOrderNum);

    /**
     * 通过订单号更新续租总时间
     * @param currentOrderNum
     */
    void updateResidueDay(@Param("currentNum") Long currentOrderNum);

    /**
     * 根据订单获取租借地点
     * @param currentOrderNum
     * @return
     */
    String getBookStore(@Param("currentNum") Long currentOrderNum);

    /**
     * 将历史订单插入新的临时表
     * @param account
     */
    void createBookHistory(@Param("userAccount") Long account);

    /**
     * 获取订单号List
     * @return
     */
    List<Long> getCurrentOrder();

    /**
     * 获取书籍List
     * @return
     */
    List<String> getBookNameList();

    /**
     * 获取租借地List
     * @return
     */
    List<String> getBookStoreList();

    /**
     * 获取租借天数List
     * @return
     */
    List<Long> getDayList();

    /**
     * 清空历史表
     */
    void clearBookHistory();

    /**
     * 插入新的订单
     * @param bookOrderModel
     */
    void insertNewOrder(BookOrderModel bookOrderModel);

    /**
     *  从订单表中获取用户租借的书籍列表
     * @param account
     * @return
     */
    List<String> getBookNameFromOrderTable(@Param("accountUser") Long account);

    /**
     * 书籍名、是否评价暂存表
     * @param account
     */
    void createBookOrderBookNameList(@Param("accountUser") Long account);

    /**
     * 在BOOK_ORDER_BOOKNAME_LIST获取书名
     * @return
     */
    List<String> getBookNameInBookOrderNameList();

    /**
     * 在BOOK_ORDER_BOOKNAME_LIST获取是否评分
     * @return
     */
    List<Integer> getBooleanTalkInBookOrderNameList();

    /**
     * 清空书籍名、是否评价暂存表
     */
    void clearTableBookOrderBookName();

    /**
     * 更新书籍评论状态
     * @param account
     * @param bookName
     */
    void uploadBookBooleanTalk(@Param("accountUser") Long account ,@Param("bookNameOrder") String bookName);
}