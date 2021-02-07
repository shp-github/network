package com.shp.dev.network.common.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/1/16 14:05
 * @PackageName: com.shp.dev.network.common.util.validation
 * @ProjectName: network
 */


/*
元注解：
　　元注解的作用就是负责注解其他注解

1.@Target 描述注解的使用范围 如包、类、接口、变量、方法、构造方法
    1.CONSTRUCTOR:用于描述构造器
    2.FIELD:用于描述域
    3.LOCAL_VARIABLE:用于描述局部变量
    4.METHOD:用于描述方法
    5.PACKAGE:用于描述包
    6.PARAMETER:用于描述参数
    7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
    如：@Target(ElementType.TYPE)

2.@Retention, 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效
    1.SOURCE:在源文件中有效（即源文件保留）
    2.CLASS:在class文件中有效（即class保留）
    3.RUNTIME:在运行时有效（即运行时保留）

3.@Documented,描述其它类型的annotation应该被作为被标注的程序成员的公共API，
        因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。


4.@Inherited 是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，
        则这个annotation将被用于该class的子类。

    1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
    2.String类型
    3.Class类型
    4.enum类型
    5.Annotation类型
    6.以上所有类型的数组

https://www.cnblogs.com/peida/archive/2013/04/24/3036689.html

**/

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented

@Repeatable(IsName.List.class)

//实现类
@Constraint(validatedBy = {IsNameConstraintValidator.class})
public @interface IsName {

    //默认提示消息
    String message() default "";

    //springboot自定义验证注解报错 解决办法
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    //校验字符
    String prefix() default "";

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IsName[] value();
    }

}
