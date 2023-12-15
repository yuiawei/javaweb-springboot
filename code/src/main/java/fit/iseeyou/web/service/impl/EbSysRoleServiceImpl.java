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

}
