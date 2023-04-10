/**
 * 文件名：BookFriendTalkController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.controller;

import com.xiaobai.entity.BookFriendTalkModel;
import com.xiaobai.entity.BookUserModel;
import com.xiaobai.service.BookFriendTalkService;
import com.xiaobai.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/10 15:21
 */
@Controller
@RequestMapping("/bookFriend")
public class BookFriendTalkController {
    private BookFriendTalkService bookFriendTalkService;

    @Autowired
    public BookFriendTalkController(BookFriendTalkService bookFriendTalkService) {
        this.bookFriendTalkService = bookFriendTalkService;
    }

    @RequestMapping(value = "/getFriendTalk", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getFriendTalk(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        String talk = bookFriendTalkService.getFriendTalk(Long.parseLong(jsonObject.getString("account")));
        return talk;
    }

    @RequestMapping(value = "/getBookNameAndBoolTalk", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getBookNameAndBoolTalk(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        String nameAndTalk = bookFriendTalkService.getBookNameAndBooleanTalk(Long.parseLong(jsonObject.getString("account")));
        return nameAndTalk;
    }

    @RequestMapping(value = "/addTalk", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String addTalk(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        boolean b = bookFriendTalkService.addTalk(Long.parseLong(jsonObject.getString("account")), jsonObject.getString("bookName"), jsonObject.getString("talk"));
        if(b){
            return "true";
        }else{
            return "发布书评失败，请重试！";
        }
    }

    @RequestMapping(value = "/deleteTalk", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String deleteTalk(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        //json转dto
        BookFriendTalkModel bookFriendTalkModel = JsonUtils.jsonToPojo(msg, BookFriendTalkModel.class);
        boolean b = bookFriendTalkService.deleteTalk(bookFriendTalkModel);
        if(b){
            return "true";
        }else{
            return "删除失败！";
        }
    }
}
