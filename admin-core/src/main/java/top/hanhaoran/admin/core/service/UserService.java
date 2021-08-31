package top.hanhaoran.admin.core.service;

import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.entity.User;

@Service
public interface UserService {
    String getAll();
    User insert(User user);
}
