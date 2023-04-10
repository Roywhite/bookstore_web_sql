/**
 * 文件名：BookAdminController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.controller;

import com.xiaobai.entity.BookAdminModel;
import com.xiaobai.entity.BookUserModel;
import com.xiaobai.service.BookAdminService;
import com.xiaobai.service.BookBooksService;
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
 * @date 2019/5/9 16:32
 */
@Controller
@RequestMapping("/bookadmin")
public class BookAdminController {
    private BookAdminService bookAdminService;
    @Autowired
    public BookAdminController(BookAdminService bookAdminService){
        this.bookAdminService = bookAdminService;
    }

    /**
     * 获取json格式，进行登录操作
     *
     * @param msg      json格式数据
     * @param req
     * @param response
     * @throws Exception
     */
    @RequestMapping("/loginAdmin")
    public @ResponseBody
    String loginAdmin(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookAdminModel bookAdminModel = JsonUtils.jsonToPojo(msg, BookAdminModel.class);
        //查询账户密码匹配在一起的用户是否存在
        boolean booleanAccountExist = bookAdminService.booleanAccount(bookAdminModel);
        if (booleanAccountExist) {
            return "YES";
        } else {
            return "NO";
        }
    }


    @RequestMapping(value = "/rentAndReturnBookLocal", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String rentAndReturnBookLocal(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        String s = bookAdminService.rentAndReturnBookLocal(Long.parseLong(jsonObject.getString("name")), jsonObject.getString("password"), jsonObject.getString("aesText"));
        if ("true".equals(s)) {
            return "true";
        } else if("false".equals(s)){
            return "false";
        }else{
            return s;
        }
    }
}
