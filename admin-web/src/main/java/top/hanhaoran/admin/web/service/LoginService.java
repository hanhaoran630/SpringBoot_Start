package top.hanhaoran.admin.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dao.UserMapper;
import top.hanhaoran.admin.core.dto.UserDTO;
import top.hanhaoran.admin.util.SmartBeanUtil;
import top.hanhaoran.admin.util.redis.RedisUtil;
import top.hanhaoran.admin.util.response.ResponseInfo;
import top.hanhaoran.admin.web.login.LoginDetailVO;
import top.hanhaoran.admin.web.login.LoginFormDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Service
public class LoginService {
    @Autowired
    private UserMapper userDao;
    @Autowired
    private LoginTokenService loginTokenService;
    @Autowired
    private RedisUtil redisUtil;

    public ResponseInfo<Object> login(@Valid LoginFormDTO loginForm, HttpServletRequest request) {
//        String redisVerificationCode = redisValueOperations.get(loginForm.getCodeUuid());
//        //增加删除已使用的验证码方式 频繁登录
//        redisValueOperations.getOperations().delete(loginForm.getCodeUuid());
//        if (StringUtils.isEmpty(redisVerificationCode)) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
//        if (!redisVerificationCode.equalsIgnoreCase(loginForm.getCode())) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
        String loginPwd = loginForm.getLoginPwd();
        UserDTO employeeDTO = userDao.login(loginForm.getLoginName(), loginPwd);
        if (null == employeeDTO) {
            return ResponseInfo.error("该用户不存在");
        }
        //jwt token赋值
        String compactJws = loginTokenService.generateToken(employeeDTO);

        LoginDetailVO loginDetailVO = SmartBeanUtil.copy(employeeDTO, LoginDetailVO.class);

        loginDetailVO.setToken(compactJws);

        redisUtil.set(loginDetailVO.getId().toString(),loginDetailVO);

        return ResponseInfo.successData(loginDetailVO);
    }
}
