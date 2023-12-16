package top.zhanglingxi.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * JavaBean representing the 'eb_auth_user_role' table.
 *
 * @author ChatGPT3.5
 */
@Data
public class EbSysUserRoleDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建人ID
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

