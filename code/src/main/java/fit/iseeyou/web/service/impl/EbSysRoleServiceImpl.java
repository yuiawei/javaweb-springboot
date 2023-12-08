package fit.iseeyou.web.service.impl;

import fit.iseeyou.web.domain.EbSysRoleDomain;
import fit.iseeyou.web.mapper.EbSysRoleMapper;
import fit.iseeyou.web.service.IEbSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbSysRoleServiceImpl implements IEbSysRoleService {
    @Autowired
    private EbSysRoleMapper ebSysRoleMapper;

    @Override
    public EbSysRoleDomain getRoleById(Long id) {
        return ebSysRoleMapper.getRoleById(id);
    }

    @Override
    public List<EbSysRoleDomain> getList(EbSysRoleDomain role) {
        return ebSysRoleMapper.getList(role);
    }

    @Override
    public int insertRole(EbSysRoleDomain role) {
        return ebSysRoleMapper.insertRole(role);
    }

    @Override
    public int updateRole(EbSysRoleDomain role) {
        return ebSysRoleMapper.updateRole(role);
    }

    @Override
    public int deleteRole(Long id) {
        return ebSysRoleMapper.deleteRole(id);
    }
}
