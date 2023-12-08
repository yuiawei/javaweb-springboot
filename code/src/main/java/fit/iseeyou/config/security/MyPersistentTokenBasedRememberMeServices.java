package fit.iseeyou.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
public class MyPersistentTokenBasedRememberMeServices extends PersistentTokenBasedRememberMeServices {
    public MyPersistentTokenBasedRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        Object paramValue = request.getAttribute("rememberMe");
        if (paramValue == null
                || !paramValue.toString().equalsIgnoreCase("true")
                && !paramValue.toString().equalsIgnoreCase("on")
                && !paramValue.toString().equalsIgnoreCase("yes")
                && !paramValue.equals("1")) {
            this.logger.debug(LogMessage.format("Did not send remember-me cookie (principal did not set parameter '%s')", parameter));
            return false;
        } else {
            return true;
        }
    }
}
