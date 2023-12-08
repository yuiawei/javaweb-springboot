package fit.iseeyou.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单类
 * @author Zhang Wenxu
 */
@Data
public class EbSysMenuDomain implements Serializable {
    public static final Long SerialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 页面路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型：0 菜单 1 按钮
     */
    private int type;

    /**
     * 父ID
     */
    private Long parentId;

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

    /**
     * 备注
     */
    private String remark;
}
