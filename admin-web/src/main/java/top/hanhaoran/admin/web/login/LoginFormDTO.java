package top.hanhaoran.admin.web.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="user对象",description="用户对象user")
public class LoginFormDTO {
    @NotNull(message = "登录名不能为空")
    @ApiModelProperty(example = "sa")
    private String loginName;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(example = "123456")
    private String loginPwd;

    @ApiModelProperty(value = "验证码uuid")
    private String codeUuid;

    @ApiModelProperty(value = "验证码")
    private String code;
}
