package laudhoot.config.core;

import javax.sql.DataSource;

import laudhoot.config.web.security.CustomAccessDeniedHandler;
import laudhoot.config.web.security.CustomAuthenticationFailureHandler;
import laudhoot.config.web.security.CustomLogoutSuccessHandler;
import laudhoot.config.web.security.CustomSavedRequestAwareAuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	protected void globalUserDetails(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO - encoded password in database and default user creation logic
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		auth.userDetailsService(userDetailsService)
		/* .passwordEncoder(encoder) */;

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
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
