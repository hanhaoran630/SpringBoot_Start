package top.hanhaoran.admin.web.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import top.hanhaoran.admin.util.response.ResponseInfo;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    @Autowired
    private HttpServletRequest request;

    protected <T>ResponseInfo<T> success(T data){
        return ResponseInfo.successData(data);
    }
    protected ResponseInfo fail(String msg){
        return ResponseInfo.error(msg);
    }
}
