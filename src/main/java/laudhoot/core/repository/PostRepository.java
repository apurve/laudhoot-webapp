package laudhoot.core.repository;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.rest.Post;

@Repository
public interface PostRepository extends BaseRepository<Post, Long> {

}
