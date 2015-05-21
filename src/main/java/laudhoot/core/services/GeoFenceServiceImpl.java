package laudhoot.core.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.GeoLocation;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.GeoLocationRepository;
import laudhoot.core.util.LaudhootDomainValidator;
import laudhoot.core.util.LaudhootValidator;
import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.domain.ServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class GeoFenceServiceImpl implements GeoFenceService {

	@Autowired
	private GeoFenceRepository fenceRepository;

	@Autowired
	private GeoLocationRepository locationRepository;

	@Autowired
	private LaudhootDomainValidator validator;

	@Override
	public GeoFenceTO create(GeoFenceTO geofenceTO, BindingResult result) {
		LaudhootValidator.isNotNull(geofenceTO, "Geofence cannot be null.");
		validator.validate(geofenceTO, result, ServiceRequest.CreateGeoFence.class);
		if(result.hasErrors()){
			return geofenceTO;
		}
		GeoFence geofence = new GeoFence(geofenceTO);
		GeoLocation center = geofence.getCenter();
		locationRepository.save(center);
		geofence.setCenter(center);
		geofence = fenceRepository.save(geofence);
		return new GeoFenceTO(geofence);
	}

	@Override
	public GeoFenceTO fetch(String code) {
		return new GeoFenceTO(fenceRepository.findByCode(code));
	}

	@Override
	public Set<GeoFenceTO> fetchAll() {
		List<GeoFence> geoFences =  (List<GeoFence>) fenceRepository.findAll();
		Set<GeoFenceTO> geoFenceTOs = new HashSet<GeoFenceTO>();
		for(GeoFence geoFence : geoFences){
			geoFenceTOs.add(new GeoFenceTO(geoFence));
		}
		return geoFenceTOs;
	}

}
