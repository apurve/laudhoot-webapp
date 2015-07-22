package laudhoot.core.repository.security;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.security.UserInfo;
import laudhoot.core.repository.BaseRepository;

@Repository
public interface UserInfoRepository extends BaseRepository<UserInfo, Long>{
	
	public UserInfo findByUsername(String username);
	
}
