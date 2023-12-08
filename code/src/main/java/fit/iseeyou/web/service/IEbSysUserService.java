package fit.iseeyou.web.service;


import fit.iseeyou.web.domain.EbSysUserDomain;

import java.util.List;

public interface IEbSysUserService {
    List<EbSysUserDomain> getList(EbSysUserDomain user);

    EbSysUserDomain getUserByUsername(String username);

    EbSysUserDomain getUserById(Long id);

    int insertUser(EbSysUserDomain user);

    int updateUser(EbSysUserDomain user);

    int deleteUser(Long id);
}
