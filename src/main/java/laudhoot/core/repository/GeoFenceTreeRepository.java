package laudhoot.core.repository;

import org.springframework.stereotype.Repository;

import laudhoot.core.domain.GeoFenceTreeNode;

@Repository
public interface GeoFenceTreeRepository extends BaseRepository<GeoFenceTreeNode, Long> {

	public GeoFenceTreeNode findByCode(String code);
	
}
