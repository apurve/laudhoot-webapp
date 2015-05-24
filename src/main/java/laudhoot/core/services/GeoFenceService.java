package laudhoot.core.services;

import java.util.Set;

import org.springframework.validation.BindingResult;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.domain.CoordinateTO;

public interface GeoFenceService {

	/**
	 * Creates a geofence, returns an existing one if code of the geofence already exists.
	 * */
	public GeoFenceTO createGeoFence(GeoFenceTO geofenceTO);
	
	/**
	 * Find the geofence in which provided GeoLocation lies and is not expired.
	 * 
	 * @param location - Coordinate to find in fences.
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO findGeoFence(CoordinateTO locationTO);
	
	/**
	 * Fetches a geofence from database based on the provided code.
	 * 
	 * @param code - unique code of the geofence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO fetchGeoFence(String code);
	
	/**
	 * Fetches a geofence from database based on the provided id.
	 * 
	 * @param id - unique id of the geofence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO fetchGeoFence(Long id);
	
	/**
	 * Fetches all geofences from database.
	 * 
	 * @return all {@link GeoFence}(s)
	 * */
	public Set<GeoFenceTO> fetchAllGeoFences();

}
