package laudhoot.core.services;

import java.util.Set;

import org.springframework.validation.BindingResult;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.domain.GeoFenceTO;

public interface GeoFenceService {

	/**
	 * Creates a geofence, returns an existing one if code of the geofence already exists.
	 * */
	public GeoFenceTO create(GeoFenceTO geofenceTO, BindingResult result);
	
	/**
	 * Fetches a geofence from database based on the provided code.
	 * 
	 * @param code - unique code of the geofence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO fetch(String code);
	
	/**
	 * Fetches all geofences from database.
	 * 
	 * @return all {@link GeoFence}(s)
	 * */
	public Set<GeoFenceTO> fetchAll();

}
