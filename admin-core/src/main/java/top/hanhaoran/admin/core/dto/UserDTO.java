package top.hanhaoran.admin.core.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("登录账号")
    private String username;
}
