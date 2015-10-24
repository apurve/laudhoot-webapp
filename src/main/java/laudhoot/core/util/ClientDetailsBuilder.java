package laudhoot.core.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import laudhoot.core.domain.security.OauthClientDetails;

public final class ClientDetailsBuilder {
	private final String clientId;

	private Collection<String> authorizedGrantTypes = new LinkedHashSet<String>();

	private Collection<String> authorities = new LinkedHashSet<String>();

	private Integer accessTokenValiditySeconds;

	private Integer refreshTokenValiditySeconds;

	private Collection<String> scopes = new LinkedHashSet<String>();

	private Collection<String> autoApproveScopes = new HashSet<String>();

	private String secret;

	private String registeredRedirectUris;

	private String resourceIds;

	private boolean autoApprove;

	public OauthClientDetails build() {
		final String commaSepatator = ",";
		String tempString;
		int index;
		
		OauthClientDetails result = new OauthClientDetails();
		result.setClientId(clientId);
		result.setAccessTokenValidity(accessTokenValiditySeconds);
		result.setRefreshTokenValidity(refreshTokenValiditySeconds);
		result.setClientSecret(secret);
		result.setWebServerRedirectUri(registeredRedirectUris);
		result.setResourceIds(resourceIds);
		result.setAdditionalInformation(null);
		result.setAutoapprove(String.valueOf(autoApprove));
		
		tempString = "";
		index = 0;
		for(String grantType : authorizedGrantTypes) {
			if(index == 0)
				tempString = grantType;
			else 
				tempString = tempString + commaSepatator + grantType;
			index++;
		}
		result.setAuthorizedGrantTypes(tempString);
		
		tempString = "";
		index = 0;
		for(String scope : scopes) {
			if(index == 0)
				tempString = scope;
			else 
				tempString = tempString + commaSepatator + scope;
			index++;
		}
		result.setScope(tempString);
		
		tempString = "";
		index = 0;
		for(String authority : authorities) {
			if(index == 0)
				tempString = authority;
			else 
				tempString = tempString + commaSepatator + authority;
			index++;
		}
		result.setAuthorities(tempString);
		return result;
	}

	public ClientDetailsBuilder resourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
		return this;
	}

	public ClientDetailsBuilder redirectUris(String registeredRedirectUris) {
		this.registeredRedirectUris = registeredRedirectUris;
		return this;
	}

	public ClientDetailsBuilder authorizedGrantTypes(String... authorizedGrantTypes) {
		for (String grant : authorizedGrantTypes) {
			this.authorizedGrantTypes.add(grant);
		}
		return this;
	}

	public ClientDetailsBuilder accessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
		return this;
	}

	public ClientDetailsBuilder refreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
		return this;
	}

	public ClientDetailsBuilder secret(String secret) {
		this.secret = secret;
		return this;
	}

	public ClientDetailsBuilder scopes(String... scopes) {
		for (String scope : scopes) {
			this.scopes.add(scope);
		}
		return this;
	}

	public ClientDetailsBuilder authorities(String... authorities) {
		for (String authority : authorities) {
			this.authorities.add(authority);
		}
		return this;
	}

	public ClientDetailsBuilder autoApprove(boolean autoApprove) {
		this.autoApprove = autoApprove;
		return this;
	}

	public ClientDetailsBuilder autoApprove(String... scopes) {
		for (String scope : scopes) {
			this.autoApproveScopes.add(scope);
		}
		return this;
	}

	public ClientDetailsBuilder(String clientId) {
		this.clientId = clientId;
	}

}
