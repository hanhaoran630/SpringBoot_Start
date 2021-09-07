package top.hanhaoran.admin.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.entity.User;

@Service
public interface IUserService extends IService<User> {
    String getAll();
}
