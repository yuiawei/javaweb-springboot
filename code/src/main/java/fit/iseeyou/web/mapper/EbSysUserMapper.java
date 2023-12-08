package fit.iseeyou.web.mapper;


import fit.iseeyou.web.domain.EbSysUserDomain;

import java.util.List;

public interface EbSysUserMapper {
    EbSysUserDomain getUserByUsername(String username);

    EbSysUserDomain getUserById(Long id);

    List<EbSysUserDomain> getList(EbSysUserDomain user);

    int insertUser(EbSysUserDomain user);

    int updateUser(EbSysUserDomain user);

    int deleteUser(Long id);
}
