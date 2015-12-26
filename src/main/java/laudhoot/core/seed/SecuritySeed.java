package laudhoot.core.seed;

import java.util.HashSet;
import java.util.Set;

import laudhoot.config.core.SeedConfig.DatabaseSeed;
import laudhoot.core.domain.security.UserAuthority;
import laudhoot.core.repository.security.UserAuthorityRepository;
import laudhoot.core.services.security.UserInfoService;
import laudhoot.web.domain.UserInfoTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SecuritySeed implements DatabaseSeed {
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public void seed() {
		if (userAuthorityRepository.count() < 1) {
			UserAuthority authority = new UserAuthority("USER");
			userAuthorityRepository.save(authority);
			authority = new UserAuthority("ADMIN");
			userAuthorityRepository.save(authority);
		}

		if (userInfoService.availableUsers() < 1) {
			Set<String> authorities = new HashSet<String>();
			authorities.add("USER");
			UserInfoTO user = new UserInfoTO("password", "user", authorities,
					true, true, true, true);
			userInfoService.createUserInfo(user);

			user.setId(null);
			user.setUsername("admin");
			authorities.add("ADMIN");
			user.setAuthorities(authorities);
			userInfoService.createUserInfo(user);
		}
	}

}
