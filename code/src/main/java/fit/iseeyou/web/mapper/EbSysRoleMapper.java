package fit.iseeyou.web.mapper;


import fit.iseeyou.web.domain.EbSysRoleDomain;

import java.util.List;

public interface EbSysRoleMapper {
    EbSysRoleDomain getRoleById(Long id);

    List<EbSysRoleDomain> getList(EbSysRoleDomain role);

    int insertRole(EbSysRoleDomain role);

    int updateRole(EbSysRoleDomain role);

    int deleteRole(Long id);
}
