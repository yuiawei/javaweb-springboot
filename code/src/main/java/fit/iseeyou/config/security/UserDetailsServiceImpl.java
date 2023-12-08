package fit.iseeyou.config.security;

import fit.iseeyou.web.domain.EbSysRoleDomain;
import fit.iseeyou.web.domain.EbSysUserDomain;
import fit.iseeyou.web.mapper.EbSysRoleMapper;
import fit.iseeyou.web.mapper.EbSysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private EbSysUserMapper ebSysUserMapper;
    @Autowired
    private EbSysRoleMapper ebSysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EbSysUserDomain user = ebSysUserMapper.getUserByUsername(username);
        if (Objects.isNull(user)) {
            LOGGER.error("根据username = {}未查询到用户对象", username);
            throw new UsernameNotFoundException("用户名错误");
        }
        // TODO 查询用户权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
//        List<EbSysRoleDomain> roles = ebSysRoleMapper.getRoleByUserId(user.getId());
        List<EbSysRoleDomain> roles = new ArrayList<>();
        for (EbSysRoleDomain role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
            authorities.add(authority);
        }
        return new MyUserDetails(username, user.getPassword(), authorities);
    }
}
