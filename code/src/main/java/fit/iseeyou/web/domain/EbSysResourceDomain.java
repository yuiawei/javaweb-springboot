package fit.iseeyou.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源实体类
 * @author 文心一言
 */
@Data
public class EbSysResourceDomain implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 菜单ID
     */
    private Long menuId;

    /**
     * 请求方法，如GET、POST等
     */
    private String method;

    /**
     * URL地址
     */
    private String url;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 备注
     */
    private String remark;

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