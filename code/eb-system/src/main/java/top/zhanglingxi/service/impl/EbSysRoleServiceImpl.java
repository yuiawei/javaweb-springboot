package top.zhanglingxi.service.impl;

import top.zhanglingxi.mapper.EbSysRoleMapper;
import top.zhanglingxi.service.IEbSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EbSysRoleServiceImpl implements IEbSysRoleService {
    @Autowired
    private EbSysRoleMapper ebSysRoleMapper;

}
