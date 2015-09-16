package laudhoot.config.core;

import javax.sql.DataSource;

import laudhoot.config.web.security.CustomAccessDeniedHandler;
import laudhoot.config.web.security.CustomAuthenticationFailureHandler;
import laudhoot.config.web.security.CustomLogoutSuccessHandler;
import laudhoot.config.web.security.CustomSavedRequestAwareAuthenticationSuccessHandler;
import laudhoot.config.web.security.CustomUserApprovalHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * OAuth 2.0 security configuration.
 * 
 * @author apurve
 */
@Configuration
public class OAuth2SecutiryConfig {

	private static final String LAUDHOOT_RESOURCE_ID = "laudhoot";
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(LAUDHOOT_RESOURCE_ID).stateless(true);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				// Since we want the protected resources to be accessible in the UI as well we need 
				// session creation to be allowed (it's disabled by default in 2.0.6)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
				.requestMatchers().antMatchers("/shout/**")
			.and()
				.requestMatchers().antMatchers("/oauth/users/**")
			.and()
				.requestMatchers().antMatchers("/oauth/clients/**")
			.and()
				.authorizeRequests()
					.antMatchers("/shout/**").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))");
			
			// @formatter:on
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private DataSource dataSource;
		
		@Autowired
		private TokenStore tokenStore;

		@Autowired
		private UserApprovalHandler userApprovalHandler;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Value("http://localhost:8080/laudhoot/oauth/redirect")
		private String laudhootRedirectUri;
		
		@Autowired
		private ClientDetailsService clientDetailsService;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients.jdbc(dataSource)/*.withClient("laudhoot-app")
			 			.resourceIds(LAUDHOOT_RESOURCE_ID)
			 			.authorizedGrantTypes("password", "client_credentials")
			 			.authorities("ROLE_CLIENT")
			 			.scopes("read", "write")
			 			.secret("secret")
			 			.autoApprove(false)
			 			.redirectUris(laudhootRedirectUri)
			 			.accessTokenValiditySeconds(600)
			 			.refreshTokenValiditySeconds(6000)*/;
			// @formatter:on
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
					.authenticationManager(authenticationManager).setClientDetailsService(clientDetailsService);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.realm("laudhoot/client");
		}
		
	}
	
	protected static class Stuff {
		
		@Autowired
		private ClientDetailsService clientDetailsService;

		@Autowired
		private TokenStore tokenStore;

		@Bean
		public ApprovalStore approvalStore() throws Exception {
			TokenApprovalStore store = new TokenApprovalStore();
			store.setTokenStore(tokenStore);
			return store;
		}

		@Bean
		@Lazy
		@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
		public CustomUserApprovalHandler userApprovalHandler() throws Exception {
			CustomUserApprovalHandler handler = new CustomUserApprovalHandler();
			handler.setApprovalStore(approvalStore());
			handler.setRequestFactory(new DefaultOAuth2RequestFactory(
					clientDetailsService));
			handler.setClientDetailsService(clientDetailsService);
			handler.setUseApprovalStore(true);
			return handler;
		}
	}

}