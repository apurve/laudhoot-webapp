package laudhoot.web.controller.rest;

import javax.servlet.http.HttpServletResponse;

import laudhoot.core.services.security.ClientDetailsService;
import laudhoot.web.domain.ClientTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/client")
public class ClientController extends BaseRestController {

	@Autowired
	ClientDetailsService clientDetailsService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ClientTO registerNewClient(@RequestBody ClientTO clientTO,
			BindingResult result, HttpServletResponse response) {
		clientTO.setValidation(result);
		clientTO = clientDetailsService.createClient(clientTO);
		if (clientTO.getValidation().hasErrors()) {
			clientTO.populateValidatonErrors();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return clientTO;
	}

}
