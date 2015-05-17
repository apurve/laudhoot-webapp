package laudhoot.core.repository;

import laudhoot.core.domain.GeoFence;

import org.springframework.stereotype.Repository;

/**
 * An interface for a repository of GeoFences.
 * 
 * @author Apurve Gupta
 */

@Repository
public interface GeoFenceRepository extends BaseRepository<GeoFence, Long> {

	// Find all videos with a matching name (e.g., Video.name)
	public GeoFence findByCode(String code);

}
