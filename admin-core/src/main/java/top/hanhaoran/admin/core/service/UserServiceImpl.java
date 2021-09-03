package top.hanhaoran.admin.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.bo.UserBO;
import top.hanhaoran.admin.core.dao.UserDao;
import top.hanhaoran.admin.core.entity.User;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private static final ConcurrentHashMap<Long, UserBO> userCache = new ConcurrentHashMap<>();

    public String getAll(){
        return userDao.selectList(null).toString();
    }


    public UserBO getById(Long id) {
        UserBO userBO = userCache.get(id);
        if (userBO == null) {
            User user = userDao.selectById(id);
            if (user != null) {
                userBO = new UserBO(user);
                userCache.put(user.getId(), userBO);
            }
        }
        return userBO;
    }

    public Long insert(User user) {
        return null;
    }
}
