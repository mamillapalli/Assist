package com.csme.admin.assist.jwtauthentication.configuration;

import com.csme.admin.assist.jwtauthentication.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Profile("JWT")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Value("${ldap.url}")
    private String ldapUrl;
    @Value("${ldap.managerDn}")
    private String ldapManagerDn;
    @Value("${ldap.managerPassword}")
    private String ldapManagerPassword;

    @Value("${cors.allowedURLS}")
    private String corsAllowedUrls;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      //  auth.userDetailsService(myUserDetailsService);
//        auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=springframework,dc=org")
//                .and()
//                .passwordCompare()
//               // .passwordEncoder(new BCryptPasswordEncoder())
//               // .passwordEncoder(new )
//                .passwordAttribute("userPassword");
        System.out.println("ldap url is " + ldapUrl);
        System.out.println("ldap manager Dn is " + ldapManagerDn);
        System.out.println("ldap manager password is " + ldapManagerPassword);
        System.out.println("corsAllowedUrls is " + corsAllowedUrls);

            auth.ldapAuthentication()

                    // .userDnPatterns("CN={0},OU=Office365")
                    //  .groupSearchBase("OU=groups")
                    .userSearchBase("OU=Office365")
                   // .userSearchFilter("CN={0}").
                     .userSearchFilter("(userPrincipalName={0})")


                    .contextSource()
                    //.url("ldap://chinasystems-me.com/DC=chinasystems-me,DC=com");

                    .url(ldapUrl).managerDn(ldapManagerDn).managerPassword(ldapManagerPassword);
            // .and()
            // .passwordCompare()
            // .passwordEncoder(new BCryptPasswordEncoder())
            // .passwordEncoder(new )
            // .passwordAttribute("userPassword");

        System.out.println("authenticated without exception");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        //System.out.println("cors urls are " + String.valueOf(Arrays.asList(corsAllowedUrls.split(";"))));
        System.out.println("corsAllowedUrls corsConfigurer is " + corsAllowedUrls);
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               //registry.addMapping("/**").allowedOrigins(String.valueOf(Arrays.asList(corsAllowedUrls.split(";")))).allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedOrigins(Arrays.asList(corsAllowedUrls.split(";")));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("jwt"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=springframework,dc=org")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("userPassword");
//    }
}
