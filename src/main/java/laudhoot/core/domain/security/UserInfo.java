package laudhoot.core.domain.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import laudhoot.core.domain.BaseDomain;
import laudhoot.web.domain.UserInfoTO;

@Entity
public class UserInfo extends BaseDomain {

	private String password;
	private String username;
	@ManyToMany
	private Set<UserAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	

	public UserInfo() {
		super();
	}

	public UserInfo(String password, String username,
			Set<UserAuthority> authorities, boolean accountNonExpired,
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

	public UserInfo(UserInfoTO userInfoTO) {
		this.password = userInfoTO.getPassword();
		this.username = userInfoTO.getUsername();
		this.authorities = new HashSet<UserAuthority>();
		for (String userAuthority : userInfoTO.getAuthorities()) {
			this.authorities.add(new UserAuthority(userAuthority));
		}
		this.accountNonExpired = userInfoTO.isAccountNonExpired();
		this.accountNonLocked = userInfoTO.isAccountNonLocked();
		this.credentialsNonExpired = userInfoTO.isCredentialsNonExpired();
		this.enabled = userInfoTO.isEnabled();
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
	}

	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<UserAuthority> authorities) {
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
