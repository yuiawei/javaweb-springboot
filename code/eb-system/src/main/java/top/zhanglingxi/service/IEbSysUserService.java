package top.zhanglingxi.service;



import top.zhanglingxi.domain.EbSysUserDomain;

import java.util.List;

public interface IEbSysUserService {
    List<EbSysUserDomain> getList(EbSysUserDomain user);
}
