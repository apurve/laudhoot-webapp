package laudhoot.web.controller.view;

import java.util.Set;

import laudhoot.core.services.GeoFenceService;
import laudhoot.web.domain.GeoFenceTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/geofence")
public class GeoFenceController extends BaseController {

	@Autowired
	private Logger logger;

	@Autowired
	private GeoFenceService geoFenceService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createGeoFence(ModelMap map) {
		map.put("geofence", new GeoFenceTO());
		return "geofence";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createGeoFence(ModelMap map, @ModelAttribute("geofence") GeoFenceTO geoFenceTO, BindingResult result) {
		geoFenceTO.validation = result;
		geoFenceTO = geoFenceService.createGeoFence(geoFenceTO);
		map.put("geofence", geoFenceTO);
		if (result.hasErrors()) {
			return "geofence";
		}
		return "redirect:view";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody Set<GeoFenceTO> viewAllGeoFences() {
		return geoFenceService.fetchAllGeoFences();
	}

}