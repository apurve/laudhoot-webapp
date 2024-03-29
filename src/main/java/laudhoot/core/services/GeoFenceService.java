package laudhoot.core.services;

import java.util.Set;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.GeoFenceTO;

public interface GeoFenceService {

	/**
	 * Creates a geoFence, returns an existing one if code of the geoFence already exists.
	 * */
	public GeoFenceTO createGeoFence(GeoFenceTO geofenceTO);
	
	/**
	 * Find the geoFence in which provided GeoLocation lies and is not expired.
	 * 
	 * @param location - Coordinate to find in fences.
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO findGeoFence(CoordinateTO locationTO);
	
	/**
	 * Find the geoFence in which provided GeoLocation lies and is not expired.
	 * 
	 * @param latitude - of the location to find in fences.
	 * @param longitude - of the location to find in fences.
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO findGeoFence(Double latitude, Double longitude);
	
	/**
	 * Fetches a geoFence from database based on the provided code.
	 * 
	 * @param code - unique code of the geoFence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO fetchGeoFence(String code);
	
	/**
	 * Fetches a geoFence from database based on the provided id.
	 * 
	 * @param id - unique id of the geoFence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFenceTO fetchGeoFence(Long id);
	
	/**
	 * Fetches all geoFence from database.
	 * 
	 * @return all {@link GeoFence}(s)
	 * */
	public Set<GeoFenceTO> fetchAllGeoFences();

	/**
	 * Find the code of geoFence in which provided GeoLocation lies and is not expired.
	 * 
	 * @param location - Coordinate to find in fences.
	 * 
	 * @return String - code of geoFence
	 * */
	public String findGeoFenceCode(CoordinateTO locationTO);
	
	/**
	 * Check if the geofence is fenceable in parent geofence.
	 * 
	 * @param parentCode - code of the parent geofence
	 * @param code - code of the geofence
	 * 
	 * @return boolean - ture if fenceable, false otherwise
	 * */
	public boolean isFenceableInParent(String parentCode, String code);
	
	public interface Fenceable<T> {
		boolean fences(T o);
	}

}
