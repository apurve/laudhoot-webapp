package laudhoot.core.services;

import laudhoot.web.domain.UserInfoTO;

import org.springframework.security.core.userdetails.User;

public interface UserInfoService {

	/**
	 * Creates a {@link UserInfo}
	 * 
	 * @param userDetails - {@link User} spring implementation of the user to be created
	 * 
	 * @return {@link User} if created successfully, null otherwise.
	 * */
	public UserInfoTO createUserInfo(UserInfoTO userDetails);
	
	/**
	 * Counts the number of available users.
	 * 
	 * @return number of available users.
	 * */
	public long availableUsers();
}
