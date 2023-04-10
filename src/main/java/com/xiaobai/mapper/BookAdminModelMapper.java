package com.xiaobai.mapper;

import com.xiaobai.entity.BookAdminModel;

public interface BookAdminModelMapper {

    /**
     * 判断账号和密码是否匹配
     * @param bookAdminModel
     * @return
     */
    Integer booleanUserAndPass(BookAdminModel bookAdminModel);
}