package top.hanhaoran.admin.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.hanhaoran.admin.core.entity.User;
import top.hanhaoran.admin.core.service.UserService;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(ModelMap modelMap){
        User user=new User();
        user.setUsername("213");
        user.setPassword("123");
        userService.insert(user);
        modelMap.addAttribute("msg",userService.getAll());

        return "index";
    }
}
