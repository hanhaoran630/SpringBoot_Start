package top.hanhaoran.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanhaoran.admin.util.redis.RedisUtil;

@SpringBootTest
public class SampleTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSelect() {
        redisUtil.set("1", "1");
        String a = (String) redisUtil.get("1");
    }

}