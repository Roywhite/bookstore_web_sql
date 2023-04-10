/**
 * 文件名：BookUserModelController
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：注册登录相关方法
 */

package com.xiaobai.controller;

import com.xiaobai.entity.BookUserModel;
import com.xiaobai.service.BookUserService;
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
 * @date 2019/4/21 15:23
 */
@Controller
@RequestMapping("/bookuser")
public class BookUserController {

    private BookUserService bookUserService;

    @Autowired
    public BookUserController(BookUserService bookUserService) {
        this.bookUserService = bookUserService;
    }

    /**
     * 获取json格式，进行登录操作
     *
     * @param msg      json格式数据
     * @param req
     * @param response
     * @throws Exception
     */
    @RequestMapping("/login")
    public @ResponseBody
    String login(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //查询账户密码匹配在一起的用户是否存在
        boolean booleanAccountExist = bookUserService.booleanAccount(bookUser);
        if (booleanAccountExist) {
            return "YES";
        } else {
            return "NO";
        }
    }


    /**
     * 先查重，再插入
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public @ResponseBody
    String register(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //存账号的方法
        boolean insertAccount = bookUserService.insertAccount(bookUser);
        if (insertAccount) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/forget")
    public @ResponseBody
    String forget(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过账号和密保标识取出密码，false为账号与密保不匹配，否则返回密码
        String booleanPassword = bookUserService.getPassword(bookUser);
        return booleanPassword;
    }

    /**
     * 更新昵称
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/editPersonName")
    public @ResponseBody
    String editPersonName(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        boolean booleanUpdate = bookUserService.updatePersonName(jsonObject.getString("newName"), Long.parseLong(jsonObject.getString("account")));
        if (booleanUpdate) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/editPersonPasswd")
    public @ResponseBody
    String editPersonPasswd(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        boolean booleanUpdate = bookUserService.updatePersonPasswd(Long.parseLong(jsonObject.getString("account")), jsonObject.getString("oldPasswd"), jsonObject.getString("newPasswd"));
        if (booleanUpdate) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/getName", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getName(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取昵称
        String name = bookUserService.getName(bookUser);
        return name;
    }


    @RequestMapping("/getScore")
    public @ResponseBody
    String getScore(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取分数
        String score = bookUserService.getScore(bookUser);
        return score;
    }

    /**
     * 判断用户是否有租借中的订单
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getBooleanRent")
    public @ResponseBody
    String getBooleanRent(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取租赁状态
        Integer booleanRent = bookUserService.getBooleanRent(bookUser);
        //0代表未租借,1代表租借中
        if (booleanRent == 0) {
            return "false";
        } else {
            return "true";
        }

    }

    @RequestMapping(value = "/getBookName", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getBookName(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取租赁中的书本名字
        String bookName = bookUserService.getBookName(bookUser);
        return bookName;
    }

    /**
     * 获得用户剩余的续租次数
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getResidueDegree")
    public @ResponseBody
    String getResidueDegree(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取续租次数
        String residueDegree = bookUserService.getResidueDegree(bookUser);
        return residueDegree;
    }

    /**
     * 获取剩余的租借时间
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRestDay")
    public @ResponseBody
    String getRestDay(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //通过用户获取租借剩余时间
        Long restDay = bookUserService.getRestDay(bookUser);
        return String.valueOf(restDay);
    }

    /**
     * 续租功能
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateResidueDay")
    public @ResponseBody
    String updateResidueDay(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        //更新租借时间
        bookUserService.updateResidueDay(bookUser);
        return "true";
    }

    @RequestMapping("/updateScore")
    public @ResponseBody
    String updateScore(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        boolean b = bookUserService.updateScore(bookUser);
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/getNowBook", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getNowBook(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        String nowBook = bookUserService.getNowBook(bookUser);
        return nowBook;
    }

    @RequestMapping(value = "/getHistoryBook", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getHistoryBook(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        String historyBook = bookUserService.getHistoryBook(bookUser);
        return historyBook;
    }

    /**
     * 获取收货地址
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getReceiverAddress", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String getReceiverAddress(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        String receiverAddress = bookUserService.getReceiverAddress(bookUser);
        if (!"".equals(receiverAddress) && receiverAddress != null) {
            return receiverAddress;
        } else {
            return "";
        }
    }

    /**
     * 更新收货地址
     *
     * @param msg
     * @param req
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateReceiverAddress", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String updateReceiverAddress(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        BookUserModel bookUser = JsonUtils.jsonToPojo(msg, BookUserModel.class);
        boolean b = bookUserService.updateReceiverAddress(bookUser);
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/rentBook", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String rentBook(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        boolean boolAesText = bookUserService.rentBook(jsonObject.getString("aesText"));
        if (boolAesText) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/updateBookScore", produces = "text/plain;charset=utf-8")
    public @ResponseBody
    String updateBookScore(@RequestBody String msg, HttpServletRequest req, HttpServletResponse response) throws Exception {
        //设置编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //json转dto
        JSONObject jsonObject = JSONObject.fromObject(msg);
        boolean b = bookUserService.updateBookScore(jsonObject.getString("bookName"), jsonObject.getString("score"));
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }
}
