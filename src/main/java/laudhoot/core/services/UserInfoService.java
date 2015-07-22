package laudhoot.core.services;

import laudhoot.core.domain.security.UserInfo;
import laudhoot.web.domain.UserInfoTO;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
	
	/**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the
     * <code>UserInfo</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    public UserInfo loadUserInfoByUsername(String username) throws UsernameNotFoundException;
}
