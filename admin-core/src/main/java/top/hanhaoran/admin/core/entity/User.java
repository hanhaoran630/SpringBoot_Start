package top.hanhaoran.admin.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data

public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String account;
    private String password;
    private String realName;
    private String nickName;
    private Date birthday;
    private String phone;
    private String email;
    private String photoUrl;
    private String identityCard;
    private Boolean isUsed;



}
