package laudhoot.core.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import laudhoot.core.domain.Coordinate;
import laudhoot.core.domain.GeoFence;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.CoordinateRepository;
import laudhoot.core.util.CoordinateManager;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.util.ServiceRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoFenceServiceImpl implements GeoFenceService {

	@Autowired
	private GeoFenceRepository fenceRepository;

	@Autowired
	private CoordinateRepository locationRepository;

	@Autowired
	private LaudhootValidator validator;

	@Override
	public GeoFenceTO createGeoFence(GeoFenceTO geofenceTO) {
		LaudhootExceptionUtils.isNotNull(geofenceTO,
				"Geofence cannot be null.");
		validator.validate(geofenceTO, geofenceTO.getValidation(),
				ServiceRequest.CreateGeoFence.class);
		if (geofenceTO.getValidation().hasErrors()) {
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
		validator.validate(locationTO, locationTO.getValidation(),
				ServiceRequest.class);
		if (locationTO.getValidation().hasErrors()) {
			return null;
		}
		return resolveGeofence(new Coordinate(locationTO));
	}

	@Override
	public GeoFenceTO findGeoFence(Double latitude, Double longitude) {
		GeoFenceTO geoFenceTO = new GeoFenceTO();
		if (latitude == null || longitude == null
				|| !CoordinateManager.isValidLongitude(longitude)
				|| !CoordinateManager.isValidLatitude(latitude)) {
			geoFenceTO.setError(true);
			return geoFenceTO;
		}
		return resolveGeofence(new Coordinate(latitude, longitude));
	}
	
	private GeoFenceTO resolveGeofence(Coordinate location) {
		// TODO - improve the algorithm to search for geoFence
		List<GeoFence> geoFences = (List<GeoFence>) fenceRepository.findAll();
		for (GeoFence geoFence : geoFences) {
			if (geoFence.getExpiresOn().isAfter(DateTime.now())) {
				Double distanceFromCenter = CoordinateManager.distanceBetween(
						geoFence.getCenter(), location);
				if (distanceFromCenter < geoFence.getRadius()) {
					return new GeoFenceTO(geoFence);
				}
			}
		}
		return null;
	}
	
	@Override
	public String findGeoFenceCode(CoordinateTO locationTO) {
		GeoFenceTO geoFenceTO = findGeoFence(locationTO);
		if(geoFenceTO != null)
			return geoFenceTO.getCode();
		return null;
	}
}
