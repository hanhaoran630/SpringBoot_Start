package top.hanhaoran.admin.util.response;


import lombok.Data;

/**
 * 返回类
 *
 * @param <T>
 * @author zhuoda
 */
@Data
public class ResponseInfo<T> {

    protected Integer code;

    protected String msg;

    protected T data;

    public ResponseInfo() {
    }

    public ResponseInfo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> ResponseInfo<T> success(){
        return new ResponseInfo(ResponseUtil.SUCCESS,"操作成功！",null);
    }
    public static <T> ResponseInfo<T> successData(T data){
        return new ResponseInfo(ResponseUtil.SUCCESS,"操作成功！",data);
    }
    public static <T> ResponseInfo<T> error(String msg){
        return new ResponseInfo(ResponseUtil.ERROR,msg,null);
    }
    @Override
    public String toString() {
        return "ResponseDTO{" + "code=" + code + ", msg='" + msg + '\''  + ", data=" + data +
                '}';
    }
}
