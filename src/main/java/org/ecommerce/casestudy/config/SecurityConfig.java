package org.ecommerce.casestudy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // boilerplate code to disable csrf and authorize requests
        http.csrf(csrf -> csrf.disable());

        // this block of code determines which requests are authenticated
        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/order/**"),
                        //sample code for main project   new AntPathRequestMatcher("/order/**"),
                        new AntPathRequestMatcher("/admin/**"),
                        new AntPathRequestMatcher("/user/**")).authenticated()
                .anyRequest().permitAll();

        // this is telling us the URL for the login page and the URL to submit the login form
        http.formLogin(formLogin -> formLogin
                // this is the URL for the login page
                .loginPage("/auth/login")
                // this is the URL to submit the login form
                .loginProcessingUrl("/auth/loginSubmit")
          .defaultSuccessUrl("/auth/home", true));

        // this is telling spring security to logout when we hit the /login/logout URL
        http.logout(formLogout -> formLogout
                .invalidateHttpSession(true)
                // this is the URL to submit the logout form
                .logoutUrl("/auth/logout")
                // this is the URL to go to after logout
                .logoutSuccessUrl("/auth/home"));

        return http.build();
    }
    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

   /* @Bean
    public HttpFirewall getHttpFirewall(){

        return new DefaultHttpFirewall();
    }*/
}
