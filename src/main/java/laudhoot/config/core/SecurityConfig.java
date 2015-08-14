package laudhoot.config.core;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import laudhoot.config.web.security.CustomAccessDeniedHandler;
import laudhoot.config.web.security.CustomAuthenticationFailureHandler;
import laudhoot.config.web.security.CustomLogoutSuccessHandler;
import laudhoot.config.web.security.CustomSavedRequestAwareAuthenticationSuccessHandler;
import laudhoot.core.domain.security.UserAuthority;
import laudhoot.core.repository.security.UserAuthorityRepository;
import laudhoot.core.services.security.UserInfoService;
import laudhoot.web.domain.UserInfoTO;

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
	private UserInfoService userInfoService;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	protected void globalUserDetails(AuthenticationManagerBuilder auth)
			throws Exception {
		//TODO - encoded password in database
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		// @formatter:off
		auth
			.userDetailsService(userDetailsService)
			/*.passwordEncoder(encoder)*/;
		
		if(userAuthorityRepository.count() < 1) {
			UserAuthority authority = new UserAuthority("USER");
			userAuthorityRepository.save(authority);
			authority = new UserAuthority("ADMIN"); 
			userAuthorityRepository.save(authority);
		}
		
		if(userInfoService.availableUsers() < 1){
			Set<String> authorities = new HashSet<String>();
			authorities.add("USER");
			UserInfoTO user = new UserInfoTO("password", "user", authorities, true, true, true, true);
			userInfoService.createUserInfo(user);
			
			user.setId(null);
			user.setUsername("admin");
			authorities.add("ADMIN");
			user.setAuthorities(authorities);
			userInfoService.createUserInfo(user);
		}
		
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
