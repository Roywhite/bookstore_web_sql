/**
 * 文件名：BookUserService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.google.common.base.Joiner;
import com.xiaobai.entity.BookBooksModel;
import com.xiaobai.entity.BookOrderModel;
import com.xiaobai.entity.BookUserModel;
import com.xiaobai.mapper.*;
import com.xiaobai.utils.AesUtils;
import com.xiaobai.utils.OrderNumUtils;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/4/21 15:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookUserService {
    @Autowired
    private BookUserModelMapper bookUserModelMapper;

    @Autowired
    private BookOrderModelMapper bookOrderModelMapper;

    @Autowired
    private BookSecModelMapper bookSecModelMapper;

    @Autowired
    private BookLocationModelMapper bookLocationModelMapper;

    @Autowired
    private BookBooksModelMapper bookBooksModelMapper;

    private String bookStoreForUpdateScore;

    /**
     * 检查账密是否正确
     * @param bookUserModel
     * @return
     */
    public boolean booleanAccount(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //密码的解密
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(),sec));
        Integer accountNum = bookUserModelMapper.booleanUserAndPass(bookUserModel);
        if(accountNum == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 先检查是否有重名的用户，再插入，返回值是（是否有重名用户，true无重名，false有重名）
     * @param bookUserModel
     * @return
     */
    public boolean insertAccount(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //密码的解密
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(),sec));
        Integer sameUser = bookUserModelMapper.booleanSameUser(bookUserModel);
        if(sameUser == 0){
            bookUserModelMapper.insertUser(bookUserModel);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 先判断账号与key匹不匹配，如果匹配进行密码的查找
     * @param bookUserModel
     * @return
     */
    public String getPassword(BookUserModel bookUserModel){
        Integer num = bookUserModelMapper.booleanUserAndBackKey(bookUserModel);
        if(num==1){
            String passwordByAccountAndKey = bookUserModelMapper.getPasswordByAccountAndKey(bookUserModel);
            //获取加密秘钥
            String sec = bookSecModelMapper.getSec();
            //密码的加密
            String aesPassword = AesUtils.aesEncrypt(passwordByAccountAndKey,sec);
            return aesPassword;
        }else{
            return "false";
        }
    }

    /**
     * 先验证账密，再查询分数
     * @param bookUserModel
     * @return
     */
    public String getScore(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(),sec));
        int score = bookUserModelMapper.getScore(bookUserModel);
        return String.valueOf(score);
    }

    /**
     * 获取租借状态
     * @param bookUserModel
     * @return
     */
    public Integer getBooleanRent(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(),sec));
        Integer booleanRent = bookUserModelMapper.getBooleanRent(bookUserModel);
        return booleanRent;
    }

    /**
     * 获得书名，先获取订单号，再去订单表里查找书名
     * @param bookUserModel
     * @return
     */
    public String getBookName(BookUserModel bookUserModel){
        //获取订单号
        Long currentOrderNum = bookUserModelMapper.getCurrentOrderNum(bookUserModel);
        //获取书名
        String bookName = bookOrderModelMapper.getBookName(currentOrderNum);
        return bookName;
    }

    /**
     * 获取剩余租借次数
     * @param bookUserModel
     * @return
     */
    public String getResidueDegree(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(),sec));
        Integer residueDegree = bookUserModelMapper.getResidueDegree(bookUserModel);
        return String.valueOf(residueDegree);
    }

    /**
     * 获得剩余租借天数
     */
    public Long getRestDay(BookUserModel bookUserModel){
        Long currentOrderNum = bookUserModelMapper.getCurrentOrderNum(bookUserModel);
        String timeGenerate = bookOrderModelMapper.getTimeGenerate(currentOrderNum);
        Integer residueDay = bookOrderModelMapper.getResidueDay(currentOrderNum);
        Long resultDay = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(timeGenerate));
            long timeStart = cal.getTimeInMillis();
            String timeNowString = sdf.format(new Date());
            cal.setTime(sdf.parse(timeNowString));
            long timeNow = cal.getTimeInMillis();
            long betweenDays = (timeNow-timeStart)/(1000*3600*24);
            resultDay = residueDay - betweenDays;
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultDay;
    }

    /**
     * 更新租借时间为14天
     * @param bookUserModel
     */
    public void updateResidueDay(BookUserModel bookUserModel){
        Long currentOrderNum = bookUserModelMapper.getCurrentOrderNum(bookUserModel);
        bookOrderModelMapper.updateResidueDay(currentOrderNum);
        //用户表续租次数-1
        bookUserModelMapper.updateResidueDegree(bookUserModel.getAccount(),0);
    }

    /**
     * 更新用户昵称
     * @param newName
     * @param account
     */
    public boolean updatePersonName(String newName,Long account){
        bookUserModelMapper.updatePersonName(account,newName);
        return true;
    }

    /**
     * 更新用户密码
     * @param account
     * @param oldPasswd
     * @param newPasswd
     * @return
     */
    public boolean updatePersonPasswd(Long account,String oldPasswd ,String newPasswd){
        BookUserModel bookUserModel = new BookUserModel();
        bookUserModel.setAccount(account);
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //密码解密
        String passwd = AesUtils.aesDecrypt(oldPasswd, sec);
        newPasswd = AesUtils.aesDecrypt(newPasswd, sec);
        bookUserModel.setPassword(passwd);
        //判断是否账号与旧密码是否匹配
        Integer accountNum = bookUserModelMapper.booleanUserAndPass(bookUserModel);
        if(accountNum==1){
            bookUserModelMapper.updatePersonPasswd(account,newPasswd);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取昵称
     * @param bookUserModel
     * @return
     */
    public String getName(BookUserModel bookUserModel){
        String name = bookUserModelMapper.getName(bookUserModel);
        return name;
    }

    /**
     * 更新分数
     * @param bookUserModel
     * @return
     */
    public boolean updateScore(BookUserModel bookUserModel){
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //密码解密
        bookUserModel.setPassword(AesUtils.aesDecrypt(bookUserModel.getPassword(), sec));
        bookUserModelMapper.updateUserScore(bookUserModel);
        return true;
    }

    /**
     * 获得现在所借书籍信息
     * @param bookUserModel
     * @return
     */
    public String getNowBook(BookUserModel bookUserModel){
        //先获取订单号
        //根据订单获取书籍名
        String bookName = getBookName(bookUserModel);
        //根据订单获取剩余时间
        Long restDay = getRestDay(bookUserModel);
        //先获取订单，然后根据订单获取租借店面
        //获取订单号
        Long currentOrderNum = bookUserModelMapper.getCurrentOrderNum(bookUserModel);
        String bookStore = bookOrderModelMapper.getBookStore(currentOrderNum);
        //根据店面获取地址
        String locationFull = bookLocationModelMapper.getLocationFull(bookStore);
        //根据书籍名和租借店面获取书本简介
        String booksProfile = bookBooksModelMapper.getBooksProfile(bookName, bookStore);
        //拼接信息
        String nowBook = bookName+"^"+restDay+"^"+bookStore+"^"+locationFull+"^"+booksProfile;
        return nowBook;
    }

    /**
     * 获取历史订单
     * @param bookUserModel
     * @return
     */
    public String getHistoryBook(BookUserModel bookUserModel){
        bookOrderModelMapper.createBookHistory(bookUserModel.getAccount());
        List<Long> currentOrder = bookOrderModelMapper.getCurrentOrder();
        List<String> bookNameList = bookOrderModelMapper.getBookNameList();
        List<String> bookStoreList = bookOrderModelMapper.getBookStoreList();
        List<Long> dayList = bookOrderModelMapper.getDayList();
        StringBuilder stringBuilder = new StringBuilder();
        for(int index = 0;index<currentOrder.size();index++){
            if(index<currentOrder.size()-1) {
                stringBuilder.append(currentOrder.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookNameList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookStoreList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(dayList.get(index));
                stringBuilder.append("^");
            }else{
                stringBuilder.append(currentOrder.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookNameList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(bookStoreList.get(index));
                stringBuilder.append("^");
                stringBuilder.append(dayList.get(index));
            }
        }
        bookOrderModelMapper.clearBookHistory();
        return stringBuilder.toString();
    }

    /**
     * 获取用户的收件地址
     * @param bookUserModel
     * @return
     */
    public String getReceiverAddress(BookUserModel bookUserModel){
        String receiverAddress = bookUserModelMapper.getReceiverAddress(bookUserModel);
        return receiverAddress;
    }

    /**
     * 更新用户的收货地址
     * @param bookUserModel
     * @return
     */
    public boolean updateReceiverAddress(BookUserModel bookUserModel){
        bookUserModelMapper.updateReceiverAddress(bookUserModel);
        return true;
    }

    /**
     * 线上租借功能 传入的值格式为（account,password,bookName,bookStore）
     * @param aesText
     * @return
     */
    public boolean rentBook(String aesText){
        BookUserModel bookUserModel = new BookUserModel();
        //解码传入的数据
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //解密文本
        String text = AesUtils.aesDecrypt(aesText, sec);
        String[] split = text.split("\\^");
        Long account = Long.parseLong(split[0]);
        //解密密码
        String password = split[1];
        String bookName = split[2];
        String bookStore = split[3];
        bookUserModel.setAccount(account);
        bookUserModel.setPassword(password);
        boolean b = booleanAccount(bookUserModel);
        if(!b){
            return false;
        }else{
            //更新用户表相关(租借状态、订单)
            //获取订单号
            Long orderNum = OrderNumUtils.getOrderNum(account);
            bookUserModelMapper.updateBoolRentAndOrderNumAndResDegree(account,1,orderNum,1L);
            //更新书籍表
            Integer bookNum = bookBooksModelMapper.getBookNowNum(bookStore,bookName);
            if(bookNum==0){
                return false;
            }
            Long bookNewNum = Long.valueOf(bookNum - 1);
            bookBooksModelMapper.updateBooksNum(bookStore,bookName,bookNewNum);
            //获取书籍编码
            Long bookCoding = bookBooksModelMapper.getBookCoding(bookName, bookStore);
            //获取发生时间
            Date dt =new Date();
            //获取信用分
            Integer score = bookUserModelMapper.getScore(bookUserModel);
            //获取收件地址
            String receiverAddress = bookUserModelMapper.getReceiverAddress(bookUserModel);
            //插入订单表
            BookOrderModel bookOrderModel = new BookOrderModel();
            bookOrderModel.setAccount(account);
            bookOrderModel.setBookCoding(bookCoding);
            bookOrderModel.setBookName(bookName);
            bookOrderModel.setCurrentScore(Long.valueOf(score));
            bookOrderModel.setOrderNum(orderNum);
            bookOrderModel.setReceiverAddress(receiverAddress);
            bookOrderModel.setRentLocationName(bookStore);
            bookOrderModel.setResidueDay(7L);
            bookOrderModel.setTimeGenerate(dt);
            bookOrderModelMapper.insertNewOrder(bookOrderModel);
            return true;
        }
    }

    /**
     * 线下租借功能 传入的值格式为（account,password,bookName,bookStore）
     * @param aesText
     * @return
     */
    public boolean rentBookFromLocal(String aesText){
        BookUserModel bookUserModel = new BookUserModel();
        //解码传入的数据
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //解密文本
        String text = AesUtils.aesDecrypt(aesText, sec);
        String[] split = text.split("\\^");
        Long account = Long.parseLong(split[0]);
        //解密密码
        String password = split[1];
        String bookName = split[2];
        String bookStore = split[3];
        bookUserModel.setAccount(account);
        bookUserModel.setPassword(password);
        boolean b = booleanAccount(bookUserModel);
        if(!b){
            return false;
        }else{
            //获取订单号
            Long orderNum = OrderNumUtils.getOrderNum(account);
            //更新用户表相关(租借状态、订单、续租次数)
            bookUserModelMapper.updateBoolRentAndOrderNumAndResDegree(account,1,orderNum,1L);
            //更新书籍表
            //获取书籍数量
            Integer bookNum = bookBooksModelMapper.getBookNowNum(bookStore,bookName);
            if(bookNum==0){
                return false;
            }
            Long bookNewNum = Long.valueOf(bookNum - 1);
            bookBooksModelMapper.updateBooksNum(bookStore,bookName,bookNewNum);
            //获取书籍编码
            Long bookCoding = bookBooksModelMapper.getBookCoding(bookName, bookStore);
            //获取发生时间
            Date dt =new Date();
            //获取信用分
            Integer score = bookUserModelMapper.getScore(bookUserModel);
            //收件地址为""
            //插入订单表
            BookOrderModel bookOrderModel = new BookOrderModel();
            bookOrderModel.setAccount(account);
            bookOrderModel.setBookCoding(bookCoding);
            bookOrderModel.setBookName(bookName);
            bookOrderModel.setCurrentScore(Long.valueOf(score));
            bookOrderModel.setOrderNum(orderNum);
            bookOrderModel.setReceiverAddress("");
            bookOrderModel.setRentLocationName(bookStore);
            bookOrderModel.setResidueDay(7L);
            bookOrderModel.setTimeGenerate(dt);
            bookOrderModelMapper.insertNewOrder(bookOrderModel);
            return true;
        }
    }

    /**
     * 归还书籍
     * @param aesText
     * @return
     */
    public boolean returnBook(String aesText){
        BookUserModel bookUserModel = new BookUserModel();
        //解码传入的数据
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //解密文本
        String text = AesUtils.aesDecrypt(aesText, sec);
        String[] split = text.split("\\^");
        Long account = Long.parseLong(split[0]);
        //解密密码
        String password = split[1];
        String bookName = split[2];
        //因为当有租借的用户在新手机登录时bookStore1为空，所以在生成二维码时前台不传bookStore过来，而是后台直接从数据库根据当前订单取
        bookUserModel.setAccount(account);
        bookUserModel.setPassword(password);
        boolean b = booleanAccount(bookUserModel);
        if(!b){
            return false;
        }else{
            //先从用户表里获取订单号
            //然后去订单表里根据订单号找到书店名
            Long currentOrderNum = bookUserModelMapper.getCurrentOrderNum(bookUserModel);
            String bookStore = bookOrderModelMapper.getBookStore(currentOrderNum);
            bookStoreForUpdateScore = bookStore;
            //然后对用户表里信用分、租赁状态、当前订单号、剩余续租次数进行修改
            //获取剩余天数
            Long restDay = getRestDay(bookUserModel);
            //获取当前信用分
            //因为上面在判断用户时对密码进行了解密，为了方便调用后面的方法，因此再次加密密码
            bookUserModel.setPassword(AesUtils.aesEncrypt(bookUserModel.getPassword(),sec));
            Long score = Long.valueOf(getScore(bookUserModel));
            //更新分数存表
            if(restDay<0){
                restDay = 0-restDay;
                if(restDay>3&&restDay<=6){
                    score = score-3;
                    bookUserModel.setScore(score);
                    bookUserModel.setPassword(AesUtils.aesEncrypt(bookUserModel.getPassword(),sec));
                    updateScore(bookUserModel);

                }
                if(restDay>6){
                    score = score-11;
                    bookUserModel.setScore(score);
                    bookUserModel.setPassword(AesUtils.aesEncrypt(bookUserModel.getPassword(),sec));
                    updateScore(bookUserModel);

                }
            }
            //更新租赁状态、当前订单号、剩余租借次数
            bookUserModelMapper.updateBoolRentAndOrderNumAndResDegree(account,0,null,1L);
            //然后对书籍表中暂存数量、租借次数、书籍评分进行修改
            //获取书籍数量
            Integer bookNum = bookBooksModelMapper.getBookNowNum(bookStore,bookName);
            //更新书籍暂存数量
            bookBooksModelMapper.updateBookStoreBookNumByRetrun(bookStore,bookName,bookNum+1);
            return true;
        }
    }

    public boolean updateBookScore(String bookName,String score){
        //先获取租借了几次
        Integer rentNum = bookBooksModelMapper.getRentNum(bookName, bookStoreForUpdateScore);
        //获取当前分数
        Double bookScoreSingle = bookBooksModelMapper.getBookScoreSingle(bookName, bookStoreForUpdateScore);
        Double scoreSum = rentNum*bookScoreSingle+Double.valueOf(score);
        double scoreNow = scoreSum/(rentNum+1.0);
        //更新次数和分数
        bookBooksModelMapper.updateBookScoreAndRentNum(rentNum+1,scoreNow,bookName,bookStoreForUpdateScore);
        return true;
    }

}
