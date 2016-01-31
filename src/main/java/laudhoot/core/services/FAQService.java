package laudhoot.core.services;

import java.util.List;

import laudhoot.web.domain.FAQTO;

public interface FAQService {

	/**
	 * Get paginated FAQs.
	 * @param faqsAvailable, FAQS already available to the client
	 * */
	List<FAQTO> getFAQs(Integer faqsAvailable);

}
