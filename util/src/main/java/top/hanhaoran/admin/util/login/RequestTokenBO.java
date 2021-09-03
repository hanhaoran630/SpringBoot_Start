package top.hanhaoran.admin.util.login;

import lombok.Getter;
import top.hanhaoran.admin.core.bo.UserBO;


@Getter
public class RequestTokenBO {

    private Long requestUserId;

    private UserBO userBO;

    public RequestTokenBO(UserBO userBO) {
        this.requestUserId = userBO.getId();
        this.userBO = userBO;
    }

    @Override
    public String toString() {
        return "RequestTokenBO{" +
                "requestUserId=" + requestUserId +
                ", employeeBO=" + userBO +
                '}';
    }
}
