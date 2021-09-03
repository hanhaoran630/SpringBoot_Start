package top.hanhaoran.admin.util.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dao.UserDao;
import top.hanhaoran.admin.core.dto.UserDTO;
import top.hanhaoran.admin.util.SmartBeanUtil;
import top.hanhaoran.admin.util.response.ResponseDTO;
import top.hanhaoran.admin.util.response.codeconst.UserResponseCodeConst;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Service
public class LoginService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTokenService loginTokenService;

    public ResponseDTO<LoginDetailVO> login(@Valid LoginFormDTO loginForm, HttpServletRequest request) {
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
            return ResponseDTO.wrap(UserResponseCodeConst.LOGIN_FAILED);
        }
        //jwt token赋值
        String compactJws = loginTokenService.generateToken(employeeDTO);

        LoginDetailVO loginDTO = SmartBeanUtil.copy(employeeDTO, LoginDetailVO.class);

        loginDTO.setXAccessToken(compactJws);

        return ResponseDTO.succData(loginDTO);
    }
}
