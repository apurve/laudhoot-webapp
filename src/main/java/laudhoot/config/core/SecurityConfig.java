package laudhoot.config.core;

import laudhoot.config.core.security.CustomAccessDeniedHandler;
import laudhoot.config.core.security.CustomAuthenticationFailureHandler;
import laudhoot.config.core.security.CustomLogoutSuccessHandler;
import laudhoot.config.core.security.CustomSavedRequestAwareAuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	protected void globalUserDetails(AuthenticationManagerBuilder auth)
			throws Exception {
		// @formatter:off
		auth
	      .inMemoryAuthentication()
	        .withUser("user")
	          .password("password")
	          .roles("USER")
	          .and()
	        .withUser("admin")
	          .password("password")
	          .roles("ADMIN","USER");
		// @formatter:on
	}
	
	@Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/static/**");
	  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
		   .authorizeRequests()
		       .antMatchers("/login.jsp").permitAll()
		       .antMatchers("/loginError").permitAll()
		       .anyRequest().authenticated()
		   .and()
		   	.exceptionHandling()
		   		.accessDeniedHandler(new CustomAccessDeniedHandler())
	       .and()
		   	.logout()
		   		.logoutUrl("/logout")
		   		.logoutSuccessHandler(new CustomLogoutSuccessHandler())
		   .and()
		   	.formLogin()
		   		.loginProcessingUrl("/login").permitAll()
		   		.failureHandler(new CustomAuthenticationFailureHandler())
		   		.successHandler(new CustomSavedRequestAwareAuthenticationSuccessHandler())
		   	.and()
		   	.httpBasic();
        // @formatter:on
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
