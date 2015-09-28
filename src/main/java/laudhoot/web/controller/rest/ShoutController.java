package laudhoot.web.controller.rest;

import java.util.List;

import laudhoot.core.services.ShoutService;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/rest/shout")
public class ShoutController extends BaseRestController {

	@Autowired
	ShoutService shoutService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShoutTO> listShoutsOfGeoFence(@RequestParam("geoFenceCode") String geoFenceCode) {
		return shoutService.getShoutsFromGeoFence(geoFenceCode);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ShoutTO createShout(@ModelAttribute ShoutTO shoutTO, BindingResult result) {
		shoutTO.setValidation(result);
		shoutTO = shoutService.createShout(shoutTO);
		shoutTO.populateValidatonErrors();
		return shoutTO;
	}
	
	@RequestMapping(value = "/reply/create", method = RequestMethod.POST)
	public ReplyTO createRepy(@ModelAttribute ReplyTO replyTO, BindingResult result) {
		replyTO.setValidation(result);
		replyTO = shoutService.createReply(replyTO);
		replyTO.populateValidatonErrors();
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
