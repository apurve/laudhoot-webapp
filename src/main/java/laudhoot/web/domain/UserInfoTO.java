package laudhoot.web.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import laudhoot.core.domain.security.UserAuthority;
import laudhoot.core.domain.security.UserInfo;

public class UserInfoTO extends BaseTO {
	
	@NotEmpty(groups = { ServiceRequest.CreateUser.class, ServiceRequest.class })
	@Length(min = 3, groups = { ServiceRequest.CreateUser.class,
			ServiceRequest.class, ServiceResponse.class })
	private String username;
	
	@NotEmpty(groups = { ServiceRequest.CreateUser.class, ServiceRequest.class })
	@Length(min = 8, groups = { ServiceRequest.CreateUser.class, ServiceRequest.class })
	@Null(groups = { ServiceResponse.class })
	private String password;
	
	@NotEmpty(groups = { ServiceRequest.CreateUser.class, ServiceRequest.class })
	private Set<String> authorities;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	public UserInfoTO(String password, String username,
			Set<String> authorities, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled) {
		super();
		this.password = password;
		this.username = username;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}
	
	public UserInfoTO(UserInfo userInfo) {
		super();
		this.password = userInfo.getPassword();
		this.username = userInfo.getUsername();
		this.authorities = new HashSet<String>();
		for (UserAuthority userAuthority : userInfo.getAuthorities()) {
			this.authorities.add(userAuthority.getAuthority());
		}
		this.accountNonExpired = userInfo.isAccountNonExpired();
		this.accountNonLocked = userInfo.isAccountNonLocked();
		this.credentialsNonExpired = userInfo.isCredentialsNonExpired();
		this.enabled = userInfo.isEnabled();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		super.setUsername(username);
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
