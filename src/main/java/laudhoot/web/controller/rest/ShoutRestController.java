package laudhoot.web.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import laudhoot.core.services.ShoutService;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/rest/shout")
public class ShoutRestController extends BaseRestController {

	@Autowired
	ShoutService shoutService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShoutTO> listShoutsOfGeoFence(@RequestParam("geoFenceCode") String geoFenceCode,
			@RequestParam(value="shoutsAvailable", required=false) Integer shoutsAvailable) {
		return shoutService.getShoutsFromGeoFence(geoFenceCode, shoutsAvailable);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShoutTO createShout(@RequestBody ShoutTO shoutTO, BindingResult result, HttpServletResponse response) {
		shoutTO.setValidation(result);
		shoutTO = shoutService.createShout(shoutTO);
		if (shoutTO != null && shoutTO.hasError()) {
			shoutTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return shoutTO;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public ReplyTO createRepy(@RequestBody ReplyTO replyTO, BindingResult result, HttpServletResponse response) {
		replyTO.setValidation(result);
		replyTO = shoutService.createReply(replyTO);
		if (replyTO != null && replyTO.hasError()) {
			replyTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return replyTO;
	}
	
	@RequestMapping(value = "/laud", method = RequestMethod.POST)
	public Long laudShout(@RequestParam("shoutId") Long shoutId) {
		return shoutService.laudShout(shoutId);
	}
	
	@RequestMapping(value = "/hoot", method = RequestMethod.POST)
	public Long hootShout(@RequestParam("shoutId") Long shoutId) {
		return shoutService.hootShout(shoutId);
	}
	
	@RequestMapping(value = "/reply/laud", method = RequestMethod.POST)
	public Long laudRepy(@RequestParam("replyId") Long replyId) {
		return shoutService.laudReply(replyId);
	}
	
	@RequestMapping(value = "/reply/hoot", method = RequestMethod.POST)
	public Long hootReply(@RequestParam("replyId") Long replyId) {
		return shoutService.hootReply(replyId);
	}
}
