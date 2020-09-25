package com.code.springtransaction.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 *
 * ClassName：BasicTransactionSynchronizationManager <br>
 * Description： Spring 连接配置管理类 * 主要用于统一分发管理获取mysql Connect
 * 作用：保证事务执行每次获取同样的connect连接配置
 * 功能：统一管理sql connect 配置连接 <br>
 *
 * @author wangxiong <br>
 * date 2020/9/22 16:00<br>
 * @version v1.0 <br>
 */
@Component
public class BasicTransactionSynchronizationManager {

    private static Connection connection;

    /**
     * Title: getConnection<br>
     * Author: wangxiong<br>
     * Description: 从Spring 容器获取数据库连接 <br>
     * Date:  17:29 <br>
     * @param
     * @return: java.sql.Connection
     */
    public Connection getConnection(){

        return connection;
    }

    /**
     * Title: bind<br>
     * Author: wangxiong<br>
     * Description: 将新的数据库连接添加至Spring 容器 <br>
     * Date:  17:27 <br>
     * @param connection
     * @return: void
     */
    public void bind(Connection connection){
        this.connection = connection;
    }

}
