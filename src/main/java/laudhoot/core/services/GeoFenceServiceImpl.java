package laudhoot.core.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoLocation;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.util.LaudhootValidator;

@Service
public class GeoFenceServiceImpl implements GeoFenceService {

	@Autowired
	private GeoFenceRepository repository;
	
	@Override
	public GeoFence create() {
		GeoFence geofence = fetch("home");
		if(LaudhootValidator.isNull(geofence)){
			geofence = new GeoFence("home", 
					new GeoLocation(-28.6827d, 77.3577d), 
					100l, 
					DateTime.now().plusHours(2));
			geofence = repository.save(geofence);
		}
		return geofence;
	}

	@Override
	public GeoFence fetch(String code) {
		return repository.findByCode(code);
	}

	@Override
	public List<GeoFence> fetchAll() {
		return (List<GeoFence>) repository.findAll();
	}

}
