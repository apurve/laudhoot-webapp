package laudhoot.core.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import laudhoot.config.core.SeedConfig.DatabaseSeed;
import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoFenceTreeNode;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.GeoFenceTreeRepository;

@Component
public class GeoFenceSeed implements DatabaseSeed {

	@Autowired
	private GeoFenceRepository geoFenceRepository;
	
	@Autowired
	private GeoFenceTreeRepository geoFenceTreeRepository;
	
	@Override
	public void seed() {
		if(geoFenceRepository.count() < 1) {
			GeoFence geoFence = new GeoFence(GeoFenceTreeNode.ROOT, null, 0, null);
			geoFenceRepository.save(geoFence);
			
			GeoFenceTreeNode geoFenceTreeNode = new GeoFenceTreeNode(geoFence.getCode(), null, null);
			geoFenceTreeRepository.save(geoFenceTreeNode);
		}
	}

}
