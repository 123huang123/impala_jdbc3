package qidong.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈橘子
 * @email 1398024451@qq.com
 * @date 2021/3/14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestResponse<T> { //
    private int code;
    private String message;
    private T response;

    public RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static RestResponse fail(Integer code, String msg) {
        return new RestResponse<>(code, msg);
    }

    public static RestResponse systemFail() {
        RestResponse a=new RestResponse(SystemCode.InnerError.getCode(),
                                        SystemCode.InnerError.getMessage());
        return a;
    }

    public static RestResponse ok() {
        SystemCode systemCode = SystemCode.OK;
        return new RestResponse<>(systemCode.getCode(), systemCode.getMessage());
    }

    public static <F> RestResponse<F> ok(F response) {
        SystemCode systemCode = SystemCode.OK;
        return new RestResponse<>(systemCode.getCode(), systemCode.getMessage(), response);
    }

}
