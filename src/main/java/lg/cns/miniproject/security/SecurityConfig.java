package lg.cns.miniproject.security;

import jakarta.servlet.DispatcherType;
import lg.cns.miniproject.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAccountDetailService accountDetailService;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;


    public SecurityConfig(CustomAccountDetailService accountDetailService, CustomAuthenticationSuccessHandler successHandler, CustomAuthenticationFailureHandler failureHandler) {
        this.accountDetailService = accountDetailService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/login", "/register", "/resources/**", "/css/**", "/js/**").permitAll()
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/employee-list", true)
                                .successHandler(successHandler) // Sử dụng Custom AuthenticationSuccessHandler
                                .failureHandler(failureHandler)
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .userDetailsService(accountDetailService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(accountDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
