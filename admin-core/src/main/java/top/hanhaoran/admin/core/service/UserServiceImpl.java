package top.hanhaoran.admin.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dao.UserDao;
import top.hanhaoran.admin.core.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public String getAll(){
        return userDao.selectList(null).toString();
    }

    @Override
    public User insert(User user) {
        int id=userDao.insert(user);
        return userDao.selectById(id);
    }
}
