package br.com.solar.crm.projectouth2;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.authorizeRequests()
                .antMatchers("/", "/oauth/**","/api/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}