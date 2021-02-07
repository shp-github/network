package com.shp.dev.network.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO @IsName 注解的实现类
 * @CreateTime: 2021/1/16 14:25
 * @PackageName: com.shp.dev.network.common.util.validation
 * @ProjectName: network
 */
public class IsNameConstraintValidator implements ConstraintValidator<IsName, String> {

    //注解中的值
    private String prefix;

    @Override
    public void initialize(IsName constraintAnnotation) {
        this.prefix = constraintAnnotation.prefix();
    }

    //value校验变量的值
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.indexOf(prefix) != -1) {
            return true;
        }

        //自定义校验错误信息
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("名字必须包含" + prefix)
                .addConstraintViolation();

        return false;

    }


}
