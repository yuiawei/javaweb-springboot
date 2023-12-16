package top.zhanglingxi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.zhanglingxi.domain.vo.LoginReqVO;

import java.sql.JDBCType;
import java.time.LocalDateTime;

@Data
@TableName("sys_login_log")
public class EbSysLoginLogDomain {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录请求数据
     */
    private String requestData;

    /**
     * 登录状态
     */
    private int loginStatus;
}
