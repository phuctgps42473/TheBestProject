package the.best.thebestproject.config.custom;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class CustomFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error(exception.getMessage());
        log.error(exception.getClass().getName());
        if (exception instanceof UsernameNotFoundException) {
           response.sendRedirect("/login?error=1");
        }
        if (exception instanceof BadCredentialsException){
            response.sendRedirect("/login?error=2");
        }
        if (exception instanceof DisabledException){
            response.sendRedirect("/login?error=3");
        }

    }
}
