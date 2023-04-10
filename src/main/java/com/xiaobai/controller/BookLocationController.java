/**
 * 文件名：BookLocationController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.controller;

import com.xiaobai.service.BookLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/8 03:09
 */
@Controller
@RequestMapping("/booklocation")
public class BookLocationController {
    private BookLocationService bookLocationService;
    @Autowired
    public BookLocationController(BookLocationService bookLocationService){
        this.bookLocationService = bookLocationService;
    }

    /**
     * 获得除了Net外的所有线下店
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBookLocationListExceptNet",produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getBookLocationListExceptNet(HttpServletRequest req, HttpServletResponse response) throws Exception{
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String bookLocationExceptNet = bookLocationService.getBookLocationExceptNet();
        return bookLocationExceptNet;
    }
}
