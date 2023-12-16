package top.zhanglingxi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhanglingxi.domain.EbSysRoleDomain;

import java.util.List;

@Mapper
public interface EbSysRoleMapper extends BaseMapper<EbSysRoleDomain> {
    /**
     * 根据用户Id获取用户的所有角色
     * @return 该用户的所有角色
     */
    List<EbSysRoleDomain> getRoleByUserId(Long userId);
}
