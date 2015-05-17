package laudhoot.core.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoLocation;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.GeoLocationRepository;
import laudhoot.core.util.LaudhootValidator;

@Service
public class GeoFenceServiceImpl implements GeoFenceService {

	@Autowired
	private GeoFenceRepository fenceRepository;
	
	@Autowired
	private GeoLocationRepository locationRepository;
	
	@Override
	public GeoFence create() {
		GeoFence geofence = fetch("home");
		if(LaudhootValidator.isNull(geofence)){
			GeoLocation center = new GeoLocation(-28.6827d, 77.3577d);
			locationRepository.save(center);
			geofence = new GeoFence("home", 
					center, 
					100l, 
					DateTime.now().plusHours(2));
			geofence = fenceRepository.save(geofence);
		}
		return geofence;
	}

	@Override
	public GeoFence fetch(String code) {
		return fenceRepository.findByCode(code);
	}

	@Override
	public List<GeoFence> fetchAll() {
		return (List<GeoFence>) fenceRepository.findAll();
	}

}
