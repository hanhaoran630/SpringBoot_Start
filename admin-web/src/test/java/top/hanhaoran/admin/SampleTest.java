package top.hanhaoran.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanhaoran.admin.util.redis.RedisUtil;
import top.hanhaoran.admin.web.login.LoginDetailDTO;

@SpringBootTest
public class SampleTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSelect() {
        LoginDetailDTO item=new LoginDetailDTO();
        item.setId(123L);
        item.setToken("token");
        redisUtil.set("1", item);
        item.setUsername("Sdfa");
        redisUtil.set("1", item);
        LoginDetailDTO a = (LoginDetailDTO) redisUtil.get("1");
        System.out.println(a);
    }

}