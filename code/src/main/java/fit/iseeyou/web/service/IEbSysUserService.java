package fit.iseeyou.web.service;


import fit.iseeyou.web.domain.EbSysUserDomain;

import java.util.List;

public interface IEbSysUserService {
    List<EbSysUserDomain> getList(EbSysUserDomain user);
}
