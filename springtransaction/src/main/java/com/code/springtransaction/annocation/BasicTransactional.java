package com.code.springtransaction.annocation;


import java.lang.annotation.*;

/**
 *
 * ClassName：com.code.springtransaction.annocation.BasicTransactional <br>
 * Description： 自定义事务注解 <br>
 *     @Target:指定在什么位置使用注解
 *     @Retention：指定注解生命周期(runtime:编译为class与jvm时)
 *     @Documented：生产javadoc文档时，所注解信息也会包含在文档中
 *     @Inherited：可以被子类继承
 * @author wangxiong <br>
 * date 2020/9/24 14:24<br>
 * @version v1.0 <br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BasicTransactional {
}
