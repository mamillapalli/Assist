package com.csme.admin.assist.configuration;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        this.logger.debug("Using Project specific SecurityConfiguration configure(HttpSecurity)");
/*        http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });
        http.formLogin();
        http.httpBasic();*/

        http .authorizeRequests().anyRequest().permitAll() .and() .formLogin().and() .httpBasic();
    }

}
