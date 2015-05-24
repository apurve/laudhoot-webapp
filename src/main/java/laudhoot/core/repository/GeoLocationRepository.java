package laudhoot.core.repository;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.Coordinate;

@Repository
public interface GeoLocationRepository extends BaseRepository<Coordinate, Long> {

}
