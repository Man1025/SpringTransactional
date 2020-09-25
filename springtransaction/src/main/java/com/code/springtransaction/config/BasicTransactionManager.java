package com.code.springtransaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * ClassName：com.code.springtransaction.config.BasicTransactionManager <br>
 * Description： 自定义封装ORM 框架
 * 作用：绑定参数/生成sql,执行sql， pojo对象/结果映射
 * 功能：此类 模拟ORM层框架 -- 执行sql的功能 <br>
 *
 * @author wangxiong <br>
 * date 2020/9/22 16:17<br>
 * @version v1.0 <br>
 */
@Component
public class BasicTransactionManager {

    @Autowired
    DataSource dataSource; // 数据源，连接数据库的框架

    @Autowired
    BasicTransactionSynchronizationManager basicTransactionSynchronizationManager; // Spring 事务配置管理类

    /**
     * Title: executeSql<br>
     * Author: wangxiong<br>
     * Description: 事务sql执行语句 <br>
     *     作用：数据操作
     *     功能：crud
     * Date:  16:26 <br>
     *
     * @param sql
     * @return: void
     */
    public void executeSql(String sql) throws SQLException {

        // 资源统一由spring分配
        Connection connection = basicTransactionSynchronizationManager.getConnection ();

        if (connection == null) {
            // 获取新的数据库连接  同时绑定到spring配置容器
            connection = dataSource.getConnection ();
            basicTransactionSynchronizationManager.bind ( connection );
        }

        // 执行SQL
        Statement statement = connection.createStatement ();
        statement.execute ( sql );
    }

}
