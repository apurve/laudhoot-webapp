package laudhoot.core.domain.rest;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import laudhoot.core.domain.BaseDomain;
import laudhoot.core.util.validation.LaudhootExceptionUtils;

@MappedSuperclass
public abstract class BaseRestDomain extends BaseDomain {

	private String clientId;
	
	@PrePersist
	protected void onCreate() {
		this.setCreatedOn(DateTime.now());
		this.setUpdatedOn(null);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		populateUsername(authentication);
		populateClientId(authentication);
		this.setUpdatedBy(null);
	}

	@PreUpdate
	protected void onPersist() {
		this.setUpdatedOn(DateTime.now());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		populateUsername(authentication);
		populateClientId(authentication);
	}
	
	private void populateUsername(Authentication authentication) {
		String createdByUsername = getUserName(authentication);
		this.setCreatedBy(LaudhootExceptionUtils.isNotEmpty(createdByUsername) ? createdByUsername : "system-generated");
	}
	
	private void populateClientId(Authentication authentication) {
		String createdByClient = getClientName(authentication);
		this.setClientId(LaudhootExceptionUtils.isNotEmpty(createdByClient) ? createdByClient : "system");
	}
	
	private String getUserName(Authentication authentication) {
		if (authentication != null) {
			if (authentication instanceof OAuth2Authentication) {
				OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
				if(oAuth2Authentication.isClientOnly()) {
					return "anonymous";
				} else {
					return ((User) oAuth2Authentication.getUserAuthentication().getPrincipal()).getUsername();
				}
			} else {
				if (authentication.getPrincipal() instanceof User) {
					return ((User) authentication.getPrincipal()).getUsername();
				}
			}
		}
		return null;
	}
	
	private String getClientName(Authentication authentication) {
		if (authentication != null) {
			if (authentication instanceof OAuth2Authentication) {
				return ((OAuth2Authentication) authentication).getOAuth2Request().getClientId();
			}
		}
		return null;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
