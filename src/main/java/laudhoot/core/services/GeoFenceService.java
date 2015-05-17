package laudhoot.core.services;

import java.util.List;

import laudhoot.core.domain.GeoFence;

public interface GeoFenceService {

	/**
	 * Creates a geofence, returns an existing one if code of the geofence already exists.
	 * */
	public GeoFence create();
	
	/**
	 * Fetches a geofence from database based on the provided code.
	 * 
	 * @param code - unique code of the geofence
	 * 
	 * @return {@link GeoFence}
	 * */
	public GeoFence fetch(String code);
	
	/**
	 * Fetches all geofences from database.
	 * 
	 * @return all {@link GeoFence}(s)
	 * */
	public List<GeoFence> fetchAll();
}
