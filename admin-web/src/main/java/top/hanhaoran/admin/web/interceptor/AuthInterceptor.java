package top.hanhaoran.admin.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.hanhaoran.admin.util.annotation.Auth;
import top.hanhaoran.admin.util.redis.RedisUtil;
import top.hanhaoran.admin.util.response.ResponseUtil;
import top.hanhaoran.admin.web.login.LoginDetailDTO;
import top.hanhaoran.admin.web.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "x-access-token";
    @Value("${access-control-allow-origin}")
    private String accessControlAllowOrigin;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.crossDomainConfig(response);


        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")) {
            return true;
        }
        if (!auth.NeedLogin())
            return true;

        //需要做token校验, 消息头的token优先于请求query参数的token
        String xHeaderToken = request.getHeader(TOKEN_NAME);
        String xRequestToken = request.getParameter(TOKEN_NAME);
        String xAccessToken = null != xHeaderToken ? xHeaderToken : xRequestToken;
        if (null == xAccessToken) {
            ResponseUtil.loginError(response,"token不存在");
            return false;
        }

        //验证Token过期时间
        Date time=loginService.getTokenExpiration(xAccessToken);
        if(time==null|| time.before(new Date())){
            ResponseUtil.loginError(response,"Token过期");
            return false;
        }

        //根据token获取登录用户
        Long userId= loginService.getTokenId(xAccessToken);
        if (null == userId) {
            ResponseUtil.loginError(response,"用户校验失败");
            return false;
        }

        LoginDetailDTO loginDetailVO= (LoginDetailDTO) redisUtil.get(userId.toString());
        if(loginDetailVO==null){
            ResponseUtil.loginError(response,"用户登录信息不存在");
            return false;
        }
        if(!loginDetailVO.getToken().equals(xAccessToken)){
            ResponseUtil.loginError(response,"用户token失效");
            return false;
        }

        return true;
    }

    /**
     * 配置跨域
     *
     * @param response
     */
    private void crossDomainConfig(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, " + "Accept, x-access-token");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires ", "-1");
    }
}
