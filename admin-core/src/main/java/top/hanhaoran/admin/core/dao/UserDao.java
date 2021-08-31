package top.hanhaoran.admin.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.hanhaoran.admin.core.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {

}