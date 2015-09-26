package laudhoot.core.repository.security;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.security.OauthClientDetails;
import laudhoot.core.repository.BaseRepository;

@Repository
public interface ClientDetailsRepository extends BaseRepository<OauthClientDetails, Long>{

}
