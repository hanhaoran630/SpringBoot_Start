package top.hanhaoran.admin.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hanhaoran.admin.core.service.IUserService;
import top.hanhaoran.admin.util.annotation.Auth;

@Controller
public class IndexController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @GetMapping(value = "api/users")
    @Auth()
    public String index(){

        return userService.getAll();
    }

    @ResponseBody
    @GetMapping(value = "api/login")
    @Auth(NeedLogin = false)
    public String login(){

        return userService.getAll();
    }

}
