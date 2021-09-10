package top.hanhaoran.admin.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.hanhaoran.admin.core.dto.UserDTO;
import top.hanhaoran.admin.core.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserDTO login(@Param("account") String account, @Param("password") String password);

}