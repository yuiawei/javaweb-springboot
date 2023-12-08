package fit.iseeyou.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体类
 */
@Data
public class EbSysRoleDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 创建人ID
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人ID
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
