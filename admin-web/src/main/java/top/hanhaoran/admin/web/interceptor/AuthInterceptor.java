package top.hanhaoran.admin.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.hanhaoran.admin.web.config.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;

        Auth auth = method.getMethodAnnotation(Auth.class);

        if(auth.NeedLogin()){
            response.setContentType("application/json; charset=utf-8");

            response.getWriter().print("{\"success\":false,\"msg\":\"NoUser\"}");
            return false;
        }
        else return true;
    }
}
