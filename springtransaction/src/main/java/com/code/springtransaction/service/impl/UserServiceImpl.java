package com.code.springtransaction.service.impl;

import com.code.springtransaction.annocation.BasicTransactional;
import com.code.springtransaction.config.BasicTransactionManager;
import com.code.springtransaction.config.BasicTransactionSynchronizationManager;
import com.code.springtransaction.entities.dto.UserInsertDTO;
import com.code.springtransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * ClassName：com.code.springtransaction.service.impl.UserServiceImpl <br>
 * Description： 用户管理实现类 <br>
 * @author wangxiong <br>
 * date 2020/9/24 13:52<br>
 * @version v1.0 <br>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BasicTransactionManager basicTransactionManager;

    @Autowired
    DataSource dataSource; // 数据源，连接数据库的框架

    @Autowired
    BasicTransactionSynchronizationManager basicTransactionSynchronizationManager; // Spring 事务配置管理类



    @Override
    public void deleteObj(String id) throws SQLException {
        // 资源统一由spring分配
        Connection connection = basicTransactionSynchronizationManager.getConnection ();

        if (connection == null) {
            // 获取新的数据库连接  同时绑定到spring配置容器
            connection = dataSource.getConnection ();
            basicTransactionSynchronizationManager.bind ( connection );
        }

        connection.setAutoCommit ( false ); // 修改事务提交方式，由原有自动提交改为手动提交
        try {
            // --todo 执行业务代码
            basicTransactionManager.executeSql ( "delete from user where id = '" + id + "'" );
            basicTransactionManager.executeSql ( "INSERT INTO user (id, name) VALUES ('" + UUID.randomUUID () + "' , 'test')" );

            int i = 1/0; // 模拟异常，事务回滚

            connection.commit (); // 正常；执行提交
        } catch (Exception e) {
            e.printStackTrace ();
            connection.rollback (); // 出现异常事务回滚
        }

    }

    @Override
    @BasicTransactional
    public void insert(UserInsertDTO userInsertDTO) throws SQLException {

        basicTransactionManager.executeSql ( "INSERT INTO user (id, name) VALUES ('" + UUID.randomUUID () + "' ," +
                " '"+ userInsertDTO.getName () +"')" );
//        int i = 1/0; // 模拟异常，事务回滚

    }
}
