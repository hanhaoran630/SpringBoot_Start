package top.hanhaoran.admin.web.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.hanhaoran.admin.core.dto.UserDTO;
import top.hanhaoran.admin.core.service.IUserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class LoginTokenService{

    /**
     * 过期时间一天
     */
    private static final int EXPIRE_SECONDS = 1 * 24 * 3600;
    /**
     * jwt加密字段
     */
    private static final String CLAIM_ID_KEY = "id";

    @Value("${jwt.key}")
    private String jwtKey;

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

        //两小时
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
     * 功能描述: 根据登陆token获取登陆信息
     *
     * @param
     * @return
     * @auther yandanyang
     * @date 2018/9/12 0012 上午 10:11
     */
    public Long getEmployeeTokenInfo(String token) {
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

}
