package top.hanhaoran.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanhaoran.admin.core.entity.User;
import top.hanhaoran.admin.core.service.UserService;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        User user=new User();
        user.setUsername("213");
        user.setPassword("123");
        System.out.println(userService.insert(user));
        System.out.println(userService.getAll());

    }

}