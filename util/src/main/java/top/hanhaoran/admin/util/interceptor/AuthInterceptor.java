package top.hanhaoran.admin.util.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        if(handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")){
            return  true;
        }
        if(auth.NeedLogin()){
            response.setContentType("application/json; charset=utf-8");

            response.getWriter().print("{\"success\":false,\"msg\":\"NoUser\"}");
            return false;
        }
        else return true;
    }
}
