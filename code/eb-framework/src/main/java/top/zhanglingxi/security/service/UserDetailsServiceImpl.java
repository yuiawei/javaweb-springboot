package top.zhanglingxi.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.zhanglingxi.domain.EbLoginUserDomain;
import top.zhanglingxi.domain.EbSysRoleDomain;
import top.zhanglingxi.domain.EbSysUserDomain;
import top.zhanglingxi.mapper.EbSysRoleMapper;
import top.zhanglingxi.mapper.EbSysUserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EbSysUserMapper ebSysUserMapper;
    @Autowired
    private EbSysRoleMapper ebSysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<EbSysUserDomain> userQw = new QueryWrapper<>();
        userQw.eq("username", username);
        EbSysUserDomain user = ebSysUserMapper.selectOne(userQw);
        if (Objects.isNull(user)) {
            log.error("根据username = {}未查询到用户对象", username);
            throw new UsernameNotFoundException("用户名错误");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<EbSysRoleDomain> roles = ebSysRoleMapper.getRoleByUserId(user.getId());
        for (EbSysRoleDomain role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
            authorities.add(authority);
        }
        return new EbLoginUserDomain(user, roles, authorities);
    }
}
