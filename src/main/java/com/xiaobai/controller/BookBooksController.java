/**
 * 文件名：BookBooksController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.controller;


import com.xiaobai.service.BookBooksService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/4/27 03:38
 */
@Controller
@RequestMapping("/bookbooks")
public class BookBooksController {

    private BookBooksService bookBooksService;
    @Autowired
    public BookBooksController(BookBooksService bookBooksService){
        this.bookBooksService = bookBooksService;
    }

    /**
     * 获取排行榜数据
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBookRankingList",produces = "text/plain;charset=utf-8")
    public @ResponseBody String getBookRankingList(HttpServletRequest req, HttpServletResponse response) throws Exception{
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String bookRankingList = bookBooksService.getBookRankingList();
        return bookRankingList;
    }

    /**
     * 获得搜索框搜索结果
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSearchList",produces = "text/plain;charset=utf-8")
    public @ResponseBody String getSearchList(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception{
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(msg);
        //通过传入的书名查询（支持模糊查询）
        String searchList = bookBooksService.getSearchList(jsonObject.getString("bookName"));
        return searchList;
    }

    /**
     * 获得租借书搜索结果
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBookRentSearchList",produces = "text/plain;charset=utf-8")
    public @ResponseBody String getBookRentSearchList(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception{
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(msg);
        //通过传入的书名和店面查询（支持模糊查询）
        String rentBookSearch = bookBooksService.getRentBookSearch(jsonObject.getString("bookName"), jsonObject.getString("bookStore"));
        return rentBookSearch;
    }
}
