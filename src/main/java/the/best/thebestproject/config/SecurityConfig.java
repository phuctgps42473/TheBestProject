package the.best.thebestproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import the.best.thebestproject.config.custom.CustomFailHandler;
import the.best.thebestproject.config.custom.CustomUserDetailsService;
import the.best.thebestproject.service.users.UsersService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomFailHandler customFailHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));


        http.formLogin(login -> {
            login.loginPage("/login");
            login.loginProcessingUrl("/p/login");
            login.defaultSuccessUrl("/home");
            login.failureHandler(customFailHandler);
            login.permitAll();
        });


        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/**").permitAll();
//            auth.requestMatchers("/login").permitAll();
//            auth.requestMatchers("/register").permitAll();

//            auth.requestMatchers("/**").authenticated();
        });

//        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);

        return new ProviderManager(provider);
    }

    @Bean
    public UserDetailsService userDetailsService(UsersService usersService) {
        return new CustomUserDetailsService(usersService);
    }

}