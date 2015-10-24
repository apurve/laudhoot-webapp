package laudhoot.web.controller.rest;

import javax.servlet.http.HttpServletResponse;

import laudhoot.core.services.GeoFenceService;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.GeoFenceTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/rest/geofence")
public class GeofenceRestController extends BaseRestController {

	@Autowired
	GeoFenceService geoFenceService;
	
	@RequestMapping
	public GeoFenceTO resolveGeofence(@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude, HttpServletResponse response) {
		GeoFenceTO geoFenceTO = geoFenceService.findGeoFence(latitude, longitude);
		if (geoFenceTO != null && geoFenceTO.hasError()) {
			geoFenceTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return geoFenceTO;
	}
}
