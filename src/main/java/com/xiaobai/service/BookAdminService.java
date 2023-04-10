/**
 * 文件名：BookAdminService
 * 版权：Copyright 2017-2022 xiaobai All Rights Reserved.
 * 描述：
 */

package com.xiaobai.service;

import com.xiaobai.entity.BookAdminModel;
import com.xiaobai.entity.BookUserModel;
import com.xiaobai.mapper.BookAdminModelMapper;
import com.xiaobai.mapper.BookSecModelMapper;
import com.xiaobai.utils.AesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaobai
 * @version 1.0
 * @date 2019/5/9 16:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BookAdminService {

    @Autowired
    private BookAdminModelMapper bookAdminModelMapper;

    @Autowired
    private BookSecModelMapper bookSecModelMapper;

    @Autowired
    private BookUserService bookUserService;

    /**
     * 检查账密是否正确
     *
     * @param bookAdminModel
     * @return
     */
    public boolean booleanAccount(BookAdminModel bookAdminModel) {
        //获取解密秘钥
        String sec = bookSecModelMapper.getSec();
        //密码的解密
        bookAdminModel.setAdminPassword(AesUtils.aesDecrypt(bookAdminModel.getAdminPassword(), sec));
        Integer accountNum = bookAdminModelMapper.booleanUserAndPass(bookAdminModel);
        if (accountNum == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 租借或归还操作
     * @param account
     * @param aesPasswd
     * @param aesText
     * @return
     */
    public String rentAndReturnBookLocal(Long account, String aesPasswd, String aesText) {
        BookAdminModel bookAdminModel = new BookAdminModel();
        bookAdminModel.setAdminAccount(account);
        bookAdminModel.setAdminPassword(aesPasswd);
        boolean b = booleanAccount(bookAdminModel);
        if (b) {
            //获取解密秘钥
            String sec = bookSecModelMapper.getSec();
            //解密文本
            String text = AesUtils.aesDecrypt(aesText, sec);
            String[] split = text.split("\\^");
            if (split.length == 4) {
                //租借
                boolean boolRent = bookUserService.rentBookFromLocal(aesText);
                if (boolRent) {
                    return "true";
                } else {
                    return "false";
                }
            } else if (split.length == 5) {
                boolean boolReturn = bookUserService.returnBook(aesText);
                if(boolReturn){
                    return "true";
                }else{
                    return "false";
                }
            } else {
                return "数据错误";
            }
        } else {
            return "权限不足！无该管理员用户！";
        }
    }
}
