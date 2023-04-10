package com.xiaobai.mapper;

import com.xiaobai.entity.BookUserModel;
import org.apache.ibatis.annotations.Param;

public interface BookUserModelMapper {
    /**
     * 判断账号和密码是否匹配
     * @param bookUserModel
     * @return
     */
    Integer booleanUserAndPass(BookUserModel bookUserModel);

    /**
     * 判断是否有重复账号
     * @param bookUserModel
     * @return
     */
    Integer booleanSameUser(BookUserModel bookUserModel);

    /**
     * 注册新账号
     * @param bookUserModel
     */
    void insertUser(BookUserModel bookUserModel);

    /**
     * 判断账号与密保标识是否匹配
     * @param bookUserModel
     * @return
     */
    Integer booleanUserAndBackKey(BookUserModel bookUserModel);

    /**
     * 通过account和key取出密码
     * @param bookUserModel
     * @return
     */
    String getPasswordByAccountAndKey(BookUserModel bookUserModel);

    /**
     * 通过account获取昵称
     * @param bookUserModel
     * @return
     */
    String getName(BookUserModel bookUserModel);

    /**
     * 通过account和key修改昵称
     * @param account
     * @param name
     */
    void updatePersonName(@Param("userAccount") Long account,@Param("newName") String name);

    /**
     * 更新密码
     * @param account
     * @param passwd
     */
    void updatePersonPasswd(@Param("userAccount") Long account,@Param("newPasswd") String passwd);

    /**
     * 查询信用分
     * @param bookUserModel
     * @return
     */
    Integer getScore(BookUserModel bookUserModel);

    /**
     * 查询租赁状态
     * @param bookUserModel
     * @return
     */
    Integer getBooleanRent(BookUserModel bookUserModel);

    /**
     * 获取订单号
     * @param bookUserModel
     * @return
     */
    Long getCurrentOrderNum(BookUserModel bookUserModel);

    /**
     * 获取用户剩余续租次数
     * @param bookUserModel
     * @return
     */
    Integer getResidueDegree(BookUserModel bookUserModel);

    /**
     * 更新续租次数
     * @param account
     * @param num
     */
    void updateResidueDegree(@Param("account") Long account,@Param("num") Integer num);

    /**
     * 更新用户信用分
     * @param bookUserModel
     */
    void updateUserScore(BookUserModel bookUserModel);

    /**
     * 获取用户的收件地址
     * @param bookUserModel
     * @return
     */
    String getReceiverAddress(BookUserModel bookUserModel);

    /**
     * 更新用户的收件地址
     * @param bookUserModel
     */
    void updateReceiverAddress(BookUserModel bookUserModel);

    /**
     * 租借/归还时对用户的租借状态和订单进行更新
     * @param account
     * @param booleanRent
     * @param orderNum
     * @param residueDegree
     */
    void updateBoolRentAndOrderNumAndResDegree(@Param("userAccount") Long account,@Param("booleanRent") Integer booleanRent,@Param("orderNum") Long orderNum,@Param("resDegree") Long residueDegree);


}