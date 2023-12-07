package fit.iseeyou.login10.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDomain implements Serializable {
    private static final Long SerialVersionUID = 1L;
    /**
     * ID
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 电话号码
     */
    private String userPhone;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 身份证
     */
    private String userCard;

    /**
     * 状态
     */
    private String userStatus;

    /**
     * 类型
     */
    private Integer userType;
}
