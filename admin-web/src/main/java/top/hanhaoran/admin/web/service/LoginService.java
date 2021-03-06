package top.hanhaoran.admin.web.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dao.UserMapper;
import top.hanhaoran.admin.core.dto.UserDTO;
import top.hanhaoran.admin.core.service.IUserService;
import top.hanhaoran.admin.util.SmartBeanUtil;
import top.hanhaoran.admin.util.redis.RedisUtil;
import top.hanhaoran.admin.web.login.LoginDetailDTO;
import top.hanhaoran.admin.web.login.LoginFormDTO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class LoginService {
    /**
     * 过期时间两小时
     */
    private static final int EXPIRE_SECONDS = 1 * 2 * 3600;
    /**
     * jwt加密字段
     */
    private static final String CLAIM_ID_KEY = "id";

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private UserMapper userDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserService userService;


    /**
     * 功能描述: 生成JWT TOKEN
     *
     * @param employeeDTO
     * @return
     * @auther yandanyang
     * @date 2018/9/12 0012 上午 10:08
     */
    public String generateToken(UserDTO employeeDTO) {
        Long id = employeeDTO.getId();
        /**将token设置为jwt格式*/
        String baseToken = UUID.randomUUID().toString();

        //
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeExpire = localDateTimeNow.plusSeconds(EXPIRE_SECONDS);
        Date from = Date.from(localDateTimeNow.atZone(ZoneId.systemDefault()).toInstant());
        Date expire = Date.from(localDateTimeExpire.atZone(ZoneId.systemDefault()).toInstant());

        Claims jwtClaims = Jwts.claims().setSubject(baseToken);
        jwtClaims.put(CLAIM_ID_KEY, id);
        String compactJws = Jwts.builder().setClaims(jwtClaims).setNotBefore(from).setExpiration(expire).signWith(SignatureAlgorithm.HS512, jwtKey).compact();


        return compactJws;

    }

    /**
     * 功能描述: 根据登陆token获取登陆ID
     *
     * @param
     * @return
     * @auther yandanyang
     * @date 2018/9/12 0012 上午 10:11
     */
    public Long getTokenId(String token) {
        Long userId = -1L;
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
            String idStr = claims.get(CLAIM_ID_KEY).toString();
            userId = Long.valueOf(idStr);
        } catch (Exception e) {
            log.error("getEmployeeTokenInfo error:{}", e);
            return null;
        }

        return userService.getById(userId)==null?null:userId;
    }

    public Date getTokenExpiration(String token) {
        Date expiration;
        try {
            expiration = Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody().getExpiration();
        } catch (Exception e) {
            log.error("getTokenTime error:{}", e);
            return null;
        }

        return expiration;
    }
    public Pair<Boolean,String> login(@Valid LoginFormDTO loginForm) {
//        String redisVerificationCode = redisValueOperations.get(loginForm.getCodeUuid());
//        //增加删除已使用的验证码方式 频繁登录
//        redisValueOperations.getOperations().delete(loginForm.getCodeUuid());
//        if (StringUtils.isEmpty(redisVerificationCode)) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
//        if (!redisVerificationCode.equalsIgnoreCase(loginForm.getCode())) {
//            return ResponseDTO.wrap(EmployeeResponseCodeConst.VERIFICATION_CODE_INVALID);
//        }
        UserDTO employeeDTO = userDao.login(loginForm.getAccount(), loginForm.getPassword());
        if (null == employeeDTO) {
            return Pair.of(false,"该用户不存在");
        }
        //jwt token赋值
        String compactJws = generateToken(employeeDTO);

        LoginDetailDTO loginDetailDTO = SmartBeanUtil.copy(employeeDTO, LoginDetailDTO.class);

        loginDetailDTO.setToken(compactJws);

        redisUtil.set(loginDetailDTO.getId().toString(),loginDetailDTO);

        return Pair.of(true,loginDetailDTO.getToken());
    }
    public LoginDetailDTO getUserInfo(Long userId){
        return (LoginDetailDTO) redisUtil.get(userId.toString());
    }
}
