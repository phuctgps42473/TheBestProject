package the.best.thebestproject.config.custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.users.UsersService;

@Service

@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {


    private final UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = null;
        String emailRegex = "^[\\p{Alnum}_+&*-]+(?:\\.[\\p{Alnum}_+&*-]+)*@(?:[\\p{Alnum}-]+\\.)+\\p{Alpha}{2,7}$";
        if (email.matches(emailRegex)) {
            users = usersService.findUserByEmail(email);
        }
        if (users == null) throw new UsernameNotFoundException("Không tìm thấy " + email);

        log.info("{}{}{}", email, users.getEmail(), users.getPassword());
        return users;
    }
}
