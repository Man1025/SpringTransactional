package com.code.springtransaction.entities.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 用户名
     */
    private String name;
}
