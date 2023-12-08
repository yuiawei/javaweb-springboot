package fit.iseeyou.web.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqVO implements Serializable {
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
