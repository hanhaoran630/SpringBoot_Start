package top.hanhaoran.admin.util.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 登录返回DTO
 *
 * @author lidoudou
 * @date 2017年12月21日上午09:06:31
 */
@Data
public class LoginDetailVO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("登录账号")
    private String username;

    @ApiModelProperty("登陆token")
    private String xAccessToken;



}
