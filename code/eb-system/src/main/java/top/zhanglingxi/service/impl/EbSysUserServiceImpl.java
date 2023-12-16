package top.zhanglingxi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.zhanglingxi.domain.EbSysUserDomain;
import top.zhanglingxi.mapper.EbSysUserMapper;
import top.zhanglingxi.service.IEbSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbSysUserServiceImpl implements IEbSysUserService {
    @Autowired
    private EbSysUserMapper ebSysUserMapper;


    @Override
    public List<EbSysUserDomain> getList(EbSysUserDomain user) {
        QueryWrapper<EbSysUserDomain> qw = new QueryWrapper<>();
        return ebSysUserMapper.selectList(qw);
    }
}
