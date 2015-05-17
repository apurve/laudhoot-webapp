package laudhoot.web.controller.view;

import java.util.Iterator;
import java.util.List;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.services.GeoFenceService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/geofence")
public class GeoFenceController extends BaseController {

	@Autowired
	private Logger logger;

	@Autowired
	private GeoFenceService geoFenceService;

	@RequestMapping(value = "/create")
	public String create(ModelMap map) {
		logger.info("START GEOFENCE CONTROLLER : /create");
		GeoFence geofence = geoFenceService.create();
		map.put("geofence", geofence);
		logger.info("END GEOFENCE CONTROLLER : /create");
		return "geofence";
	}

	@RequestMapping(value = "/view")
	public @ResponseBody List<GeoFence> view() {
		logger.info("START GEOFENCE CONTROLLER : /view");
		List<GeoFence> fences = (List<GeoFence>) geoFenceService.fetchAll();
		Iterator<GeoFence> iterator = fences.iterator();
		while (iterator.hasNext()) {
			logger.info("EXECUTING GEOFENCE CONTROLLER : "
					+ iterator.next().getEntityURI());
		}
		logger.info("END GEOFENCE CONTROLLER : /view");
		return fences;
	}

}