package top.hanhaoran.admin.web.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hanhaoran.admin.util.response.ResponseUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * [ 全局异常拦截 ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2019 1024lab.netInc. All rights reserved.
 * @date
 * @since JDK1.8
 */
@Slf4j
@ControllerAdvice
public class SmartGlobalExceptionHandler {

    /**
     * 添加全局异常处理流程
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        log.error("error:", e);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code", ResponseUtil.ERROR);

        // http 请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            jsonObject.put("msg","http 请求方式错误");

        }

        // 参数类型错误
        if (e instanceof TypeMismatchException) {
            jsonObject.put("msg","参数类型错误");

        }

        // json 格式错误
        if (e instanceof HttpMessageNotReadableException) {
            jsonObject.put("msg","json 格式错误");

        }

        // 参数校验未通过
        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            List<String> msgList = fieldErrors.stream().map(FieldError :: getDefaultMessage).collect(Collectors.toList());
            jsonObject.put("msg","参数校验未通过:"+String.join(",", msgList));

        }

        jsonObject.put("msg","系统错误");

        return jsonObject.toJSONString();
    }
}
