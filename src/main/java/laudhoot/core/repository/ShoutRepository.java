package laudhoot.core.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.rest.Shout;

@Repository
public interface ShoutRepository extends BaseRepository<Shout, Long> {

	public List<Shout> findByGeoFence(GeoFence geoFence);
	
}
