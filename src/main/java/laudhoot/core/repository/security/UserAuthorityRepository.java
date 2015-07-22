package laudhoot.core.repository.security;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.security.UserAuthority;
import laudhoot.core.repository.BaseRepository;

@Repository
public interface UserAuthorityRepository extends BaseRepository<UserAuthority, Long> {

	public UserAuthority findByAuthority(String authority);
	
}
