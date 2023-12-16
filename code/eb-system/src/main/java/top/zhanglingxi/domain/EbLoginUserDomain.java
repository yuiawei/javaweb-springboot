package top.zhanglingxi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties({"username", "password", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class EbLoginUserDomain implements UserDetails, Serializable {
    public static final Long SerialVersionUID = 1L;

    private EbSysUserDomain user;
    private List<EbSysRoleDomain> roles;
    private List<GrantedAuthority> authorities;

    public EbLoginUserDomain() {
    }

    public EbLoginUserDomain(EbSysUserDomain user, List<EbSysRoleDomain> roles, List<GrantedAuthority> authorities) {
        this.user = user;
        this.roles = roles;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
