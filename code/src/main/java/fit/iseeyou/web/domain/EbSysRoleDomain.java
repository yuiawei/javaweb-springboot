package fit.iseeyou.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体类
 */
@Data
@TableName("sys_role")
@JsonIgnoreProperties({"createTime", "updateTime"})
public class EbSysRoleDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
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
