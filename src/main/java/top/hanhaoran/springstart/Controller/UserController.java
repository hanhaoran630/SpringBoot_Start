package top.hanhaoran.springstart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("createTable")
    public String createTable() {
        String sql =
                "CREATE TABLE `user` (\n" +
                        " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " `user_name` varchar(255) NOT NULL,\n" +
                        " `user_password` varchar(255) DEFAULT NULL,\n" +
                        " PRIMARY KEY (`id`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;";
        jdbcTemplate.execute(sql);
        return "创建User表成功";
    }

}
