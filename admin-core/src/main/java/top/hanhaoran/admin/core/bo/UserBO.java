package top.hanhaoran.admin.core.bo;

import lombok.Getter;
import top.hanhaoran.admin.core.entity.User;

@Getter
public class UserBO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;


    public UserBO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

}
