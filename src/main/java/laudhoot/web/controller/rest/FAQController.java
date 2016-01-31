package laudhoot.web.controller.rest;

import java.util.List;

import laudhoot.core.services.FAQService;
import laudhoot.web.domain.FAQTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/faqs")
public class FAQController extends BaseRestController {
	
	@Autowired
	FAQService faqService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<FAQTO> listFAQS(@RequestParam(value="faqsAvailable", required=false) Integer faqsAvailable) {
		return faqService.getFAQs(faqsAvailable);
	}

}
