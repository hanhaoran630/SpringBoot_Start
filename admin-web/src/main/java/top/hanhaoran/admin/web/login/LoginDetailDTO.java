package top.hanhaoran.admin.web.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录返回DTO
 *
 * @author lidoudou
 * @date 2017年12月21日上午09:06:31
 */
@Data
public class LoginDetailDTO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("登录账号")
    private String username;

    @ApiModelProperty("登陆token")
    private String token;



}
