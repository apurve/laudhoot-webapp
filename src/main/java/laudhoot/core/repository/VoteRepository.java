package laudhoot.core.repository;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.rest.Vote;

@Repository
public interface VoteRepository extends BaseRepository<Vote, Long> {

}
