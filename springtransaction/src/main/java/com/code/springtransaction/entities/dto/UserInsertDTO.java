package com.code.springtransaction.entities.dto;


import lombok.Data;

/**
 *
 * ClassName：com.code.springtransaction.entities.dto.UserInsertDTO <br>
 * Description： user新增dto <br>
 * @author wangxiong <br>
 * date 2020/9/24 17:24<br>
 * @version v1.0 <br>
 */
@Data
public class UserInsertDTO {

    /**
     * 主键
     * 雪花生成
     */
//    private String id;

    /**
     * 用户名
     */
    private String name;
}
