package com.code.springtransaction.service;

import com.code.springtransaction.entities.dto.UserInsertDTO;

import java.sql.SQLException;

/**
 *
 * ClassName：com.code.springtransaction.service.UserService <br>
 * Description： 用户业务 <br>
 * @author wangxiong <br>
 * date 2020/9/23 15:48<br>
 * @version v1.0 <br>
 */
public interface UserService {

    /**
     * Title: deleteObj<br>
     * Author: wangxiong<br>
     * Description: 根据ID删除用户 <br>
     * Date:  14:12 <br>
     * @param id
     * @return: void
     */
     void deleteObj(String id) throws SQLException;


     /**
      * Title：UserService.java <br>
      * Package：com.code.springtransaction.service <br>
      * Description：新增用户信息 <br>
      * @author wangxiong <br>
      * date 2020/9/24 17:28 <br>
      * @version v1.0 <br>
      */
     void insert(UserInsertDTO userInsertDTO) throws SQLException;
}
