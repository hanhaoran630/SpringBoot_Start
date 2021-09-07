package top.hanhaoran.admin.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dao.UserMapper;
import top.hanhaoran.admin.core.entity.User;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public String getAll(){
        return userMapper.selectList(null).toString();
    }

}
