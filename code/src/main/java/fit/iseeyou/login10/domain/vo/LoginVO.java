package fit.iseeyou.login10.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {
    public static final Long SerialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String verifyCode;
}
