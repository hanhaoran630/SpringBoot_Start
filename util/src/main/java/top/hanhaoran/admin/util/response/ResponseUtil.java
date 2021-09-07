package top.hanhaoran.admin.util.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static final int ERROR = -1;
    public static final int LOGIN_ERROR = 0;
    public static final int SUCCESS = 200;

    /**
     * 成功输出
     *
     * @param response
     * @param msg
     * @throws IOException
     */
    public static void success(HttpServletResponse response, String msg) throws IOException {
        output(response,SUCCESS, msg, null);
    }

    /**
     * 成功输出
     *
     * @param response
     * @param msg
     * @param data
     * @throws IOException
     */
    public static <T> void success(HttpServletResponse response, String msg, T data) throws IOException {
        output(response, SUCCESS, msg, data);
    }

    /**
     * 错误输出
     *
     * @param response
     * @param code
     * @param msg
     * @throws IOException
     */
    public static void error(HttpServletResponse response, Integer code, String msg) throws IOException {
        output(response,ERROR, msg, null);
    }
    /**
     * 错误输出
     *
     * @param response
     * @param msg
     * @throws IOException
     */
    public static void loginError(HttpServletResponse response, String msg) throws IOException {
        output(response,LOGIN_ERROR, msg, null);
    }
    /**
     * 信息输出
     *
     * @param response
     * @param code
     * @param msg
     * @throws IOException
     */
    public static <T> void output(HttpServletResponse response, Integer code, String msg, T data) throws IOException {
        ResponseInfo responseInfo=new ResponseInfo(code,msg,data);
        String message = JSONObject.toJSONString(responseInfo);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
        response.flushBuffer();
    }
}
