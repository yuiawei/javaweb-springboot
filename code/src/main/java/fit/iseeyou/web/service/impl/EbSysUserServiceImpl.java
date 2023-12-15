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


}
