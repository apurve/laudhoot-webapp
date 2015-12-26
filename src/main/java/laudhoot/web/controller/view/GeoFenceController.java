package laudhoot.web.controller.view;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import laudhoot.core.services.GeoFenceService;
import laudhoot.core.services.GeoFenceTreeService;
import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.domain.GeoFenceTreeNodeTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/geofence")
public class GeoFenceController extends BaseController {

	@Autowired
	private Logger logger;

	@Autowired
	private GeoFenceService geoFenceService;
	
	@Autowired
	private GeoFenceTreeService geoFenceTreeService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createGeoFence(ModelMap map) {
		map.put("geofence", new GeoFenceTO());
		return "geofence";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createGeoFence(ModelMap map, @ModelAttribute("geofence") GeoFenceTO geoFenceTO, BindingResult result) {
		geoFenceTO.setValidation(result);
		geoFenceTO = geoFenceService.createGeoFence(geoFenceTO);
		map.put("geofence", geoFenceTO);
		if (result.hasErrors()) {
			return "geofence";
		}
		return "redirect:edit?geoFenceCode="+geoFenceTO.getCode();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editGeoFence(ModelMap map, @RequestParam("geoFenceCode") String geoFenceCode) {
		map.put("geofence", geoFenceService.fetchGeoFence(geoFenceCode));
		map.put("geofenceTreeNode", geoFenceTreeService.fetchNodeByCode(geoFenceCode));
		return "geofenceTree";
	}
	
	@RequestMapping(value = "/fence", method = RequestMethod.POST)
	public String fenceGeoFence(ModelMap map, @ModelAttribute("geofenceTreeNode") GeoFenceTreeNodeTO geoFenceTreeNodeTO,
			BindingResult result) {
		map.put("geofence", geoFenceService.fetchGeoFence(geoFenceTreeNodeTO.getCode()));
		geoFenceTreeNodeTO.setValidation(result);
		geoFenceTreeNodeTO = geoFenceTreeService.editGeoFenceNode(geoFenceTreeNodeTO);
		if(geoFenceTreeNodeTO.hasError()) {
			map.put("geofenceTreeNode", geoFenceTreeNodeTO);
			return "geofenceTree";
		}
		return "redirect:edit?geoFenceCode="+geoFenceTreeNodeTO.getCode();
	}
	
	@RequestMapping(value="/fenceable", method = RequestMethod.GET)
	public String checkFenceableInParent(@RequestParam("geofence") String geofence,
			 @RequestParam("parent") String parent, ModelMap map, HttpServletResponse response) {
		map.put("geofence", geofence);
		map.put("parent", parent);
		boolean fenceable = geoFenceService.isFenceableInParent(parent, geofence);
		map.put("fenceable", fenceable);
		if(fenceable) {
			map.put("geofenceTreeNode", new GeoFenceTreeNodeTO(geofence));
			List<String> children = geoFenceTreeService.getFenceableChildren(parent);
			children.remove(geofence);
			map.put("fenceableChildren", children);
		}
		return "geofenceNode";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody Set<GeoFenceTO> viewAllGeoFences() {
		return geoFenceService.fetchAllGeoFences();
	}

}