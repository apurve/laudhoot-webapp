package laudhoot.core.services.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import laudhoot.core.domain.security.UserAuthority;
import laudhoot.core.domain.security.UserInfo;
import laudhoot.core.repository.security.UserAuthorityRepository;
import laudhoot.core.repository.security.UserInfoRepository;
import laudhoot.core.services.UserInfoService;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.ServiceRequest;
import laudhoot.web.domain.UserInfoTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserDetailsServieImpl implements UserDetailsService, UserInfoService {

	@Autowired
	private UserInfoRepository userRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	private LaudhootValidator validator;
	
	@Override @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userRepository.findByUsername(username);
		if (userInfo != null) {
			if (CollectionUtils.isEmpty(userInfo.getAuthorities())) {
				throw new UsernameNotFoundException("the user has no granted authority(s)");
			} else {
				Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
				for(UserAuthority authority : userInfo.getAuthorities()){
					authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
				}
				return new User(userInfo.getUsername(), userInfo.getPassword(),
						userInfo.isEnabled(), userInfo.isAccountNonExpired(),
						userInfo.isCredentialsNonExpired(),
						userInfo.isAccountNonLocked(),
						authorities);
			}
		} else {
			throw new UsernameNotFoundException("the user could not be found");
		}
	}

	@Override
	public UserInfoTO createUserInfo(UserInfoTO userInfoTO) {
		LaudhootExceptionUtils.isNotNull(userInfoTO,
				"UserInfo cannot be null.");
		validator.validate(userInfoTO, userInfoTO.getValidation(),
				ServiceRequest.CreateGeoFence.class);
		if (userInfoTO.getValidation() != null && userInfoTO.getValidation().hasErrors()) {
			return userInfoTO;
		}
		UserInfo userInfo = new UserInfo(userInfoTO);
		userAuthorityRepository.save(userInfo.getAuthorities());
		userRepository.save(userInfo);
		return new UserInfoTO(userInfo);
	}

	@Override
	public long availableUsers() {
		return userRepository.count();
	}

}
