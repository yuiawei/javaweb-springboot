package fit.iseeyou.config.security;

import fit.iseeyou.config.security.custom.CustomAuthenticationEntryPoint;
import fit.iseeyou.config.security.custom.CustomPersistentTokenBasedRememberMeServices;
import fit.iseeyou.config.security.filter.CustomJwtFilter;
import fit.iseeyou.config.security.filter.CustomLoginFilter;
import fit.iseeyou.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomJwtFilter customJwtFilter;
    @Autowired
    private CustomLoginFilter customLoginFilter;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .userDetailsService(userDetailsService)
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/doLogout")
                .and()
                .addFilterAt(customLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers("/doLogin", "/vcImage").anonymous()
                .mvcMatchers("/public").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeServices(new CustomPersistentTokenBasedRememberMeServices(UUID.randomUUID().toString(),
                        userDetailsService, new InMemoryTokenRepositoryImpl()));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
