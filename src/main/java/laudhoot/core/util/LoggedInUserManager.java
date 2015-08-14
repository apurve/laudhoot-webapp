package laudhoot.core.util;

import laudhoot.core.domain.security.UserInfo;
import laudhoot.core.services.security.UserInfoService;
import laudhoot.core.util.validation.LaudhootExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public final class LoggedInUserManager {
	
	private static UserInfoService userInfoService;
	
	@Autowired(required = true)
	private LoggedInUserManager(UserInfoService injectedUserInfoService) {
		userInfoService = injectedUserInfoService;
	}
	
	public static User getUser() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return null;
	}
	
	public static String getUserName() {
		User user = getUser();
		if(user != null) {
			return user.getUsername();
		}
		return null;
	}
	
	private static UserInfo getUserInfo() throws LaudhootValidationException {
		LaudhootExceptionUtils.isNotNull(userInfoService, "UserInfoService cannot be null.");
		return userInfoService.loadUserInfoByUsername(getUserName());
	}
	
	public static String getUserInfoUri() throws LaudhootValidationException {
		UserInfo usrInfo = getUserInfo();
		if(usrInfo != null) {
			return usrInfo.getEntityURI();
		}
		return null;
	}

}