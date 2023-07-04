package qidong.util;

/**
 * @author 陈橘子
 * @email 1398024451@qq.com
 * @date 2021/3/14
 */
public enum SystemCode {
    /**
     * OK
     */
    OK(1, "成功"),
    /**
     * AccountFailure
     */
    AccountFailure(400, "用户账号失效"),
    /**
     * Unauthorized
     */
    Unauthorized(401, "用户未登录"),
    /**
     * UNAUTHORIZED
     */
    AuthError(402, "用户名或密码错误"),
    /**
     * PasswordError
     */
    PasswordError(1024, "密码错误"),

    /**
     * VerificationCodeError
     */
    VerificationCodeError(403, "验证码错误"),
    /**
     * InnerError
     */
    InnerError(500, "系统内部错误"),
    /**
     * ParameterValidError
     */
    ParameterValidError(501, "参数验证错误"),

    /**
     * AccessDenied
     */
    AccessDenied(502,"用户没有权限访问");

    int code;
    String message;

    SystemCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
