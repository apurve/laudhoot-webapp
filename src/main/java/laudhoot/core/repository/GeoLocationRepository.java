package laudhoot.core.repository;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.GeoLocation;

@Repository
public interface GeoLocationRepository extends BaseRepository<GeoLocation, Long> {

}
