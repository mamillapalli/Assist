package com.csme.admin.assist.configuration.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
@Profile("Dev")
public class SecurityConfigationDB extends WebSecurityConfigurerAdapter {

    private static final String ROLE_PREFIX = "";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //allows all requests without authentication
       httpSecurity.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
      //  httpSecurity.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

        //all requests to be authenticated
       // httpSecurity.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated().and().sessionManagement()
            //    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
//        httpSecurity.cors().disable();
//        httpSecurity.csrf().disable();


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(ROLE_PREFIX);
    }
}
