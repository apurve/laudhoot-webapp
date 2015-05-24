package laudhoot.core.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import laudhoot.core.domain.Coordinate;
import laudhoot.core.domain.GeoFence;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.GeoLocationRepository;
import laudhoot.core.util.CoordinateManager;
import laudhoot.core.util.validation.LaudhootValidationUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.domain.ServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoFenceServiceImpl implements GeoFenceService {

	@Autowired
	private GeoFenceRepository fenceRepository;

	@Autowired
	private GeoLocationRepository locationRepository;

	@Autowired
	private LaudhootValidator validator;

	@Override
	public GeoFenceTO createGeoFence(GeoFenceTO geofenceTO) {
		LaudhootValidationUtils.isNotNull(geofenceTO,
				"Geofence cannot be null.");
		validator.validate(geofenceTO, geofenceTO.getValidationResult(),
				ServiceRequest.CreateGeoFence.class);
		if (geofenceTO.getValidationResult().hasErrors()) {
			return geofenceTO;
		}
		GeoFence geofence = new GeoFence(geofenceTO);
		Coordinate center = geofence.getCenter();
		locationRepository.save(center);
		geofence.setCenter(center);
		geofence = fenceRepository.save(geofence);
		return new GeoFenceTO(geofence);
	}

	@Override
	public GeoFenceTO fetchGeoFence(String code) {
		return new GeoFenceTO(fenceRepository.findByCode(code));
	}

	@Override
	public GeoFenceTO fetchGeoFence(Long id) {
		return new GeoFenceTO(fenceRepository.findOne(id));
	}

	@Override
	public Set<GeoFenceTO> fetchAllGeoFences() {
		List<GeoFence> geoFences = (List<GeoFence>) fenceRepository.findAll();
		Set<GeoFenceTO> geoFenceTOs = new HashSet<GeoFenceTO>();
		for (GeoFence geoFence : geoFences) {
			geoFenceTOs.add(new GeoFenceTO(geoFence));
		}
		return geoFenceTOs;
	}

	@Override
	public GeoFenceTO findGeoFence(CoordinateTO locationTO) {
		validator.validate(locationTO, locationTO.getValidationResult(),
				ServiceRequest.class);
		if (locationTO.getValidationResult().hasErrors()) {
			return null;
		}
		// TODO - improve the algorithm to search for geofence
		Coordinate location = new Coordinate(locationTO);
		List<GeoFence> geoFences = (List<GeoFence>) fenceRepository.findAll();
		for (GeoFence geoFence : geoFences) {
			Double distanceFromCenter = CoordinateManager.distanceBetween(geoFence.getCenter(), location);
			if(distanceFromCenter>geoFence.getRadius()){
				return new GeoFenceTO(geoFence);
			}
		}
		return null;
	}
}
