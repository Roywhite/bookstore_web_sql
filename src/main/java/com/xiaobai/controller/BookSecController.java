/**
 * 文件名：BookSecController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.controller;

import com.xiaobai.service.BookSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/4/29 20:37
 */
@Controller
@RequestMapping("/booksec")
public class BookSecController {
    private BookSecService bookSecService;
    @Autowired
    public BookSecController(BookSecService bookSecService){
        this.bookSecService = bookSecService;
    }

    @RequestMapping(value = "/getSec",produces = "text/plain;charset=utf-8")
    public @ResponseBody String getSec(HttpServletRequest req, HttpServletResponse response) throws Exception{
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String sec = bookSecService.getSec();
        return sec;
    }
}
