import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hanhaoran.springstart.Dao.UserDao;
import top.hanhaoran.springstart.Entity.User;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private UserDao userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}