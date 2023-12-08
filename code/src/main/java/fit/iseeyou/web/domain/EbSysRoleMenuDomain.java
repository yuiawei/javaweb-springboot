package fit.iseeyou.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色-菜单
 * @author Zhang Wenxu
 */
@Data
public class EbSysRoleMenuDomain implements Serializable {
    public static final Long SerialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 菜单Id
     */
    private Long menuId;

    /**
     * 创建人id
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
