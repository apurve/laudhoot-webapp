package laudhoot.web.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import laudhoot.core.services.ShoutService;
import laudhoot.web.domain.BaseTO;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;
import laudhoot.web.domain.VoteTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public List<ReplyTO> listRepliesOfShout(@RequestParam("shoutId") Long shoutId,
			@RequestParam(value="repliesAvailable", required=false) Integer repliesAvailable) {
		return shoutService.getRepliesFromShout(shoutId, repliesAvailable);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShoutTO createShout(@RequestBody ShoutTO shoutTO, BindingResult result, HttpServletResponse response) {
		shoutTO.setValidation(result);
		shoutTO = shoutService.createShout(shoutTO);
		if (shoutTO != null && shoutTO.getValidation() != null && shoutTO.getValidation().hasErrors()) {
			shoutTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return shoutTO;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public ReplyTO createRepy(@RequestBody ReplyTO replyTO, BindingResult result, HttpServletResponse response) {
		replyTO.setValidation(result);
		replyTO = shoutService.createReply(replyTO);
		if (replyTO != null && replyTO.getValidation() != null && replyTO.getValidation().hasErrors()) {
			replyTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return replyTO;
	}
	
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public VoteTO laudShout(@RequestBody VoteTO voteTO, BindingResult result, HttpServletResponse response) {
		voteTO.setValidation(result);
		voteTO = shoutService.vote(voteTO);
		if (voteTO != null && voteTO.getValidation() != null && voteTO.getValidation().hasErrors()) {
			voteTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return voteTO;
	}
	
}
