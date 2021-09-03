package top.hanhaoran.admin.util.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.hanhaoran.admin.util.interceptor.Auth;
import top.hanhaoran.admin.util.response.ResponseDTO;

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
    public ResponseDTO<LoginDetailVO> login(@Valid @RequestBody LoginFormDTO loginForm, HttpServletRequest request) {
        return loginService.login(loginForm, request);
    }
}
