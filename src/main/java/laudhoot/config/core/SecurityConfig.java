package laudhoot.config.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /*@Override
  protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("test").password("admin").roles("TEST");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeUrls()
        .antMatchers("/aggregators/**").hasRole("TEST")
        .anyRequest().anonymous()
        .and()
        .httpBasic();
  }*/
}