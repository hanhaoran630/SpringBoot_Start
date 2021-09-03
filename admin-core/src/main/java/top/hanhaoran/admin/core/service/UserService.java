package top.hanhaoran.admin.core.service;

import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.bo.UserBO;
import top.hanhaoran.admin.core.entity.User;

@Service
public interface UserService {
    String getAll();
    UserBO getById(Long id);
    Long insert(User user);
}
