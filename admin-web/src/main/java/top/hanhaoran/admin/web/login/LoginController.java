package top.hanhaoran.admin.web.login;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.hanhaoran.admin.util.annotation.Auth;
import top.hanhaoran.admin.util.response.ResponseInfo;
import top.hanhaoran.admin.web.controller.BaseController;
import top.hanhaoran.admin.web.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="客户端-登录")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login/login")
    @ApiOperation(value = "登录", notes = "登录")
    @Auth(NeedLogin = false)
    public ResponseInfo<Object> login(@Valid @RequestBody LoginFormDTO loginForm) {
        Pair<Boolean,String> pair=loginService.login(loginForm);

        if(pair.getFirst()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("token",pair.getSecond());
            return success(jsonObject) ;
        }
        else
            return fail(pair.getSecond());
    }

    @PostMapping("/api/login/getUserInfo")
    @ApiOperation(value = "登录", notes = "登录")
    @Auth(NeedLogin = false)
    public ResponseInfo<Object> getUserInfo(Long userId) {
        return success(loginService.getUserInfo(userId));
    }
}