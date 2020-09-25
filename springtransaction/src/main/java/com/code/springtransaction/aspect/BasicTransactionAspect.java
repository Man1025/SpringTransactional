package com.code.springtransaction.aspect;

import com.code.springtransaction.annocation.BasicTransactional;
import com.code.springtransaction.config.BasicTransactionSynchronizationManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName：com.code.springtransaction.aspect.BasicTransactionAspect <br>
 * Description： 事务AOP管理 <br>
 *
 * @author wangxiong <br>
 * date 2020/9/24 15:02<br>
 * @version v1.0 <br>
 */
@Aspect
@Component
@Slf4j
public class BasicTransactionAspect {

    @Autowired
    BasicTransactionSynchronizationManager basicTransactionSynchronizationManager;

    @Autowired
    DataSource dataSource;

    /**
     * Title: transactionalPointCut<br>
     * Author: wangxiong<br>
     * Description: AOP切入点 <br>
     * Date:  11:10 <br>
     * @param
     * @return: void
     */
    @Pointcut("@annotation(com.code.springtransaction.annocation.BasicTransactional) " + // 所有使用@BasicTransactional注解的方法
            "|| @within(com.code.springtransaction.annocation.BasicTransactional)") // 目标类(target)中所有@BasicTransactional注解的所有方法
    public void transactionalPointCut() {

    }

    /**
     * Title: aroundTransactional<br>
     * Author: wangxiong<br>
     * Description: 事务执行切入点 <br>
     * Date:  15:26 <br>
     * @param point 代表事务切入点为使用@BasicTransactional注解的方法，类
     * @return: java.lang.Object
     */
    @Around ( "transactionalPointCut()" ) // 所有使用@BasicTransactional注解的方法，类，执行AOP事务处理
    public Object aroundTransactional( ProceedingJoinPoint point ) throws SQLException {

        // 通过反射获取方法与类
        MethodSignature signature = (MethodSignature) point.getSignature ();
        Class targetClass = point.getTarget ().getClass ();
        Method method = signature.getMethod ();

        /* 注解作用于类上时切入 */
        BasicTransactional targetClassAnnotation = (BasicTransactional) targetClass.getAnnotation ( BasicTransactional.class );
        /* 注解作用于方法上时切入 */
        BasicTransactional methodAnnotation = method.getAnnotation ( BasicTransactional.class );
        if (targetClassAnnotation != null || methodAnnotation != null) {

            // 资源统一由spring分配
            Connection connection = null;

            try {
                connection = basicTransactionSynchronizationManager.getConnection ();

                if (connection == null) {
                    // 获取新的数据库连接  同时绑定到spring配置容器
                    connection = dataSource.getConnection ();
                    basicTransactionSynchronizationManager.bind ( connection );
                }
                connection.setAutoCommit ( false ); // 修改提交方式，由原有自动提交改为手动提交

                // --todo 执行业务代码
                point.proceed (); // 执行事务处理

                log.info ( "开始执行AOP事务编程：{}" );

                connection.commit (); // 正常；执行提交
            } catch (Exception e) {
                e.printStackTrace ();
                connection.rollback (); // 出现异常事务回滚

                log.info ( "出现异常事务回滚：{}" );

            } catch (Throwable throwable) {
                throwable.printStackTrace ();
            }
        }

        return null;
    }
}
