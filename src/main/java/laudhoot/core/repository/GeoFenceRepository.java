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

	public GeoFence findByCode(String code);

}
