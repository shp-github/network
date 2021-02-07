package com.shp.dev.network.common.util.exception;

import com.shp.dev.network.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 统一异常处理类
 * @CreateTime: 2021/1/16 15:48
 * @PackageName: com.shp.dev.network.common.util.exception
 * @ProjectName: network
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean error(HttpServletRequest request, Exception e) {

        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            bindingResult = ex.getBindingResult();
        }
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            bindingResult = bindException.getBindingResult();
        }
        if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException parameterException = (MissingServletRequestParameterException) e;
            return ResultBean.error(parameterException.getMessage());
        }

        if (bindingResult != null) {
            ResultBean bean = new ResultBean();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                bean.put(fieldName, errorMessage);
            });
            return bean;
        }

        String url = "";
        if (Objects.nonNull(request)) {
            url = request.getServletPath();
        }
        log.error("url:{},系统异常", url, e);
        return ResultBean.error("url:" + url + ",error:" + e.getMessage());
    }
}