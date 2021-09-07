package top.hanhaoran.admin.web.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.hanhaoran.admin.util.annotation.Auth;
import top.hanhaoran.admin.util.response.ResponseInfo;
import top.hanhaoran.admin.web.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags="客户端-登录")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login/login")
    @ApiOperation(value = "登录", notes = "登录")
    @Auth(NeedLogin = false)
    public ResponseInfo<Object> login(@Valid @RequestBody LoginFormDTO loginForm, HttpServletRequest request) {
        return loginService.login(loginForm, request);
    }

    @PostMapping("/api/login/login")
    @ApiOperation(value = "登录", notes = "登录")
    @Auth(NeedLogin = false)
    public ResponseInfo<Object> getUserInfo(@Valid @RequestBody LoginFormDTO loginForm, HttpServletRequest request) {
        return loginService.login(loginForm, request);
    }
}
