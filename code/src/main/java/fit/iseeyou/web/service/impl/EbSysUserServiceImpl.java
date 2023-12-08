package fit.iseeyou.web.service.impl;

import fit.iseeyou.web.domain.EbSysUserDomain;
import fit.iseeyou.web.mapper.EbSysUserMapper;
import fit.iseeyou.web.service.IEbSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbSysUserServiceImpl implements IEbSysUserService {
    @Autowired
    private EbSysUserMapper ebSysUserMapper;

    @Override
    public List<EbSysUserDomain> getList(EbSysUserDomain user) {
        return ebSysUserMapper.getList(user);
    }

    @Override
    public EbSysUserDomain getUserByUsername(String username) {
        return ebSysUserMapper.getUserByUsername(username);
    }

    @Override
    public EbSysUserDomain getUserById(Long id) {
        return ebSysUserMapper.getUserById(id);
    }

    @Override
    public int insertUser(EbSysUserDomain user) {
        return ebSysUserMapper.insertUser(user);
    }

    @Override
    public int updateUser(EbSysUserDomain user) {
        return ebSysUserMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return ebSysUserMapper.deleteUser(id);
    }
}
